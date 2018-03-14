package implicits.usecases

case class User(name:String)
case class Request(content:String)

object RequestResponseTest extends App {

  // representing the current user and request in the implicit scope
  //在隐式范围内表示当前用户和请求
  implicit val u:User = User("user1")
  implicit val r:Request = Request("content")

  type Result = String
  //有两个认证操作 - 确定和不确定
  trait Action  // there are two auth Action - Ok and Not Ok
  case class OkAction(request:Result) extends Action
  case class UnauthorizedAction(content:Result) extends Action
  //我们简单的数据库
  var userDB = List(User("user1"), User("user2"), User("user3") ) // our simple database


  // check authorization and wraps request into Action
  //检查授权并将请求包装到Action中
  def withAuth(f: User => (Request => Result) ): Action = {
    println("withAuth in play...")
  //期望用户和请求在隐式范围内定义
    val result:Result = f(implicitly[User])(implicitly[Request]) // expecting User and Request were defined in implicit scope

    def isAuthorized(user:User): Boolean = {
      println("check authorisation...")
      true                                // authorize everyone, welcome :)
    }

    def onUnAuthorized(request: Request): Action = {
      println("wrapped to Action as not authorized")
      UnauthorizedAction("unauthorized")
    }

    if (result == "ok") {
      if ( isAuthorized(implicitly[User]) ) {
        println("wrapped to Action as authorized")
        OkAction("ok")
      } else onUnAuthorized(implicitly[Request])
    } else onUnAuthorized(implicitly[Request])
  }

  //
  def withUser(f: User => (Request => Result)) : Action = {
    println("withUser in play...")

    def findInDb(user: User): Option[User] = {
      userDB.find(u => u == user)
    }

    val authResult:Action = withAuth (    // call 'withAuth'
      /* Create anonymous function to please the 'withAuth'
         it is already known who is current user, and what request is, but doesn't what Result is.
         And he Result is the fact whether the user exists in users database or not.
         So it tries to get the Result by searching for the current user.
         If user has been found then it makes sense to continue with Authorisation (withAuth)
      */
      //传递带有用户,请求和结果的匿名函数(其中结果基于用户数据库中的用户存在)
      user => request => { // passing anonymous function with user, request, and result (where result is based on user existence in the user db )
        val userOption = findInDb(User("user1"))          // find the user in users db
        val result:Result = userOption match {      // check if user exists
          case Some(_) => // user has been found
            println("user has been found")
            "ok"
          case None    => // user has not been found
            println("user has not been found")
            "not ok"
        }
       result // "ok" / "not ok" (user has been found or not)
      } // end of passing anonymous function to 'withAuth'
    )

    authResult match {
      case OkAction(_) => f(implicitly[User])(implicitly[Request])  // if authorized do The work
    }

    authResult
  } // edn of 'withUser'

  // Let's run this and to some work
  def doWork() = withUser {                     // doWork -> withUser -> withAuth  (like Decorator/Wrapper pattern)
    user => request => {
      // if user exists (withUser) and authorization is ok (withAuth), then this work will done
      println("do some important work here!!")
      "work is done!"                           // Result is just a String
    }
  }
  val result = doWork()  // doWork doesn't care about user or request
}
/* Output:
withUser in play...
withAuth in play...
user has been found
check authorisation...
wrapped to Action as authorized
do some important work here!!
*/
package extractors

/*
 * In nutshell: If apply() method usually creates an object from/by inputs parts, as factory method,
 * 简而言之：如果apply（）方法通常通过输入部分创建一个对象，如工厂方法，
 * Then unapply method - extracts those parts from the object in favour of / (To be used in) pattern-matching
 * 然后，unapply方法 - 从对象中提取那些有利于/（将用于）模式匹配的部分
 * that's why it is called "Extractor"
 *
 * #extractor, unapply-method
 * related: #companion-object #pattern-matching #variable-binding related
 * apply方法接受构造参数变成对象,而unapply方法接受一个对象,从中提取值。
*/
object ExtractorsTest extends App {

  // let's say we have class email
  //比方说，我们有class的电子邮件
  class Email {
    var name:String = ""
    var host:String = ""
  }

  // an companion-object that contains extractor (unapply-method)
  //一个伴随对象包含提取器（unapply-method）
  object Email {
    //它通过字符串创建新电子邮件
    def apply(str:String): Email = { // it creates new Email by String
      val email = new Email()
      //将字符串转换为对象
      val ar = str.split("@")  // converts string to the object
      email.name = ar(0)
      email.host = ar(1)

      email
    }

    // extractor ! 提取器
    def unapply(email:Email) : Option[(String, String)] = { // extractor splits object apart 提取器拆分物体
      Some(email.name, email.host)              // to be able match it by name & domain 能够通过名称和域匹配它
    }

  }

  // usage:
  val email = Email("bob@gmail.com")


  // 1. #pattern-matching related 模式匹配相关

  email match {
    case Email(name, domain) => println("name: " + name, "domain:" + domain)  // (name: bob,domain:gmail.com)
    case _ => println("no")
  }

  // when we do email 'match ..' it calls 'Email.unapply(email)' and returns 'Some("bob","gmail")'  !
  //我们做电子邮件“匹配..”时，它会调用“Email.unapply（email）”并返回“Some（”bob“，”gmail“）！


  // 2. Let's make extractor to help us ever more, filtering the expecting result by pattern-matching. (#pattern-matching related)
  //2. 让我们提取器来帮助我们更多，通过模式匹配过滤期望的结果。
  object Domain {

    // extractor !
    def unapply(host: String): Option[(String)] = {

       val domains = host.split("\\.")  // splitting by dot ( "." )
      //现在我们只关心第1个域来简化事情
       if (domains.length > 1) return Some(domains(1))  // for now we care only about 1-th domain to keep things simple

       None

    }
  } // as you can see to keep things simple we even do not create apply() method, and there is no class Domain, only object is used

  email match {

    // this part is interesting 这部分很有趣  Domain.unapply（host）！
    case Email(name, host @ Domain("com")) => println ("yes, this is .com domain")  // Domain.unapply(host) !

    // what do you think "host" is here ? it has the following value: "gmail.com"
    // 你觉得“host”在这里？ 它有以下值：“gmail.com”
    // what this about? we binds (@ means 'to bind to') host to Domain.  (#variable-binding related)
    // 这是关于什么？ 我们绑定（@意味着'绑定'）host到Domain。 （＃变量绑定相关）
    // and what does it mean to bind ?? It means to pass it to extractor (to unapply method) - Domain.unapply(host)
    // 这是什么意思绑定？ 它意味着把它传递给提取器（不适用的方法） - Domain.unapply（host）
    // there is no magic, this just a syntax, we should remember: if inside "case" - that's all about unapply()

      //没有魔法，这只是一个语法，我们应该记住：如果里面的“case” - 这是关于unapply（）

    case _ => println("nothing has been matched")
  }

}

- In play it works out of the box. Not needed any setup to make it work
- DI configuration is setup in Module.scala

```scala
// app/Module.scala

import com.google.inject.AbstractModule
import App.Application._

class Module extends AbstractModule {
	def configure() = {
		bind(
			classOf[SpeakerInt])
			.to(classOf[SpanishSpeaker])
	}
}
```

>If you call this module Module and place it in the root package (app folder), it will automatically be registered with Play. Alternatively, if you want to give it a different name or put it in a different package, you can register it with Play by appending its fully qualified class name to the play.modules.enabled list in application.conf:


After specifying this mapping between Interfaces->Implementations when injected you can then use them

```scala
// app/controllers/HomeController.scala

import App.Application._

@Singleton
class HomeController @Inject() (cc: ControllerComponents, myservice: SpeakerInt) extends AbstractController(cc) {
    .....

    def say = Action {
        Ok(
            myservice.sayHello() // Hola!!, soy un servicio injectado
        )
    }

    ...
```

- You cannot create a class and expect any magic from scala in order to find your classes with a simple "import my class". Instead, you have to place (like in PHP) your classes in a Namespace (a Package)

```scala
// app/application/SpeakerInt.scala

package App.Application

trait SpeakerInt {
	def sayHello(): String
}
```


```scala
// app/application/SpanishSpeaker.scala

package App.Application

class SpanishSpeaker() extends SpeakerInt
{
	def sayHello(): String = {
		s"Hola!!, soy un servicio injectado"
	}
}
```

```scala
// app/application/EnglishSpeaker.scala

package App.Application

class EnglishSpeaker() extends SpeakerInt
{
	def sayHello(): String = {
		s"Hello im an injected service"
	}
}
```

The previosu code is to map an interface to an specific implementation. however we can also inject an implementation directly with no mapping required as we are doing with EnglishSpeaker in this new code:

```scala
class HomeController @Inject() (cc: ControllerComponents, myservice: SpeakerInt, englishSpeaker: EnglishSpeaker) extends AbstractController(cc) {

```

This service injected can be used directly with:


```scala
    def sayenglish = Action {
        Ok(
            englishSpeaker.sayHello() // Hello im an injected service
        )
    }
```
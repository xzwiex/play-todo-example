package modules

import be.objectify.deadbolt.scala.cache.HandlerCache
import be.objectify.deadbolt.scala.{DeadboltExecutionContextProvider, TemplateFailureListener}
import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}
import security.{CustomDeadboltExecutionContextProvider, DefaultTemplateFailureListener, DefaultHandlerCache}

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
class CustomDeadboltHook extends Module {
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = Seq(
    bind[TemplateFailureListener].to[DefaultTemplateFailureListener],
    bind[HandlerCache].to[DefaultHandlerCache],
    bind[DeadboltExecutionContextProvider].to[CustomDeadboltExecutionContextProvider]
  )
}

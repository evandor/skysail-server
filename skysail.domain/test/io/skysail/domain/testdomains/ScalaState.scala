package io.skysail.domain.testdomains

object ScalaState extends Enumeration {
  //type State = Value
  val NEW, Failed, Pending, Unknown = Value
}

//class State extends State
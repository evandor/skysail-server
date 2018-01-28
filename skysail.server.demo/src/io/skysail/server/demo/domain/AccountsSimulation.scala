package io.skysail.server.demo.domain

import java.util.Date

import io.skysail.api.ddd.Entity
import io.skysail.api.ui.Link
import com.fasterxml.jackson.annotation.JsonGetter

//@JsonProperty(value = "_links")
case class Account(
                    id: Option[String],
                    title: String,
                    balance: Int = 0
                    //_links:List[Link] = List(Link("self", "hier"))
                  ) { //extends Entity[String] {

//  @JsonGetter
//  val links = List(Link("self", "hier"))

  val test2:List[Link] = List(Link("hi", "there"))

}

case class OrientAccount(
    id: String,
    title: String,
    initial: Int
)

case class Transaction(
    id: Option[String], 
    from: Account, 
    to: Account, 
    date: Date, 
    amount: Int)

case class Pattern(
    id: Option[String], 
    from: Account, 
    to: Account, 
    amount: Int, 
    accounts: List[Account] = List())//, matcher: (Int) => Boolean)

//case class OrientPattern(
//    `@rid`: String,
//    id:String,
//    out_from: List[OrientNode],
//    out_to: List[OrientNode],
//    amount: Int
//)
//
//case class OrientNode(
//    out:String,
//    in: OrientAccount
//)
//
//case class OrientNodeStr(
//    out:String
//)
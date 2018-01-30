package io.skysail.server.demo.domain

import java.util.Date

import io.skysail.api.ddd.Entity
import io.skysail.api.ui.{Link, Linkable}

case class Account(
                    id: Option[String],
                    title: String,
                    balance: Int = 0
                  ) extends Entity[String] with Linkable {

  override val _links: List[Link] = List(Link("self", "here"))

}

//case class AccountList(
//                        accounts: List[Account]
//                      ) extends Linkable {
//
//  override val _links: List[Link] = List(Link("self", "here"))
//
//}

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
                    accounts: List[Account] = List())

//, matcher: (Int) => Boolean)


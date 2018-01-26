package io.skysail.server.demo.domain

import java.util.Date

import io.skysail.api.ddd.Entity

case class Account(
                    id: Option[String],
                    title: String,
                    balance: Int = 0) extends Entity[String]

case class OrientAccount(
    id: String,
    title: String,
    initial: Int
)

case class Transaction(id: Option[String], from: Account, to: Account, date: Date, amount: Int)

case class Pattern(id: Option[String], from: Account, to: Account, amount: Int, accounts: List[Account] = List())//, matcher: (Int) => Boolean)

//case class OrientPattern(
//    id:String,
//    out_from: List[OrientAccount],
//    out_to: List[OrientAccount],
//    amount: Int
//)

case class OrientPattern(
    `@rid`: String,
    id:String,
    out_from: List[OrientNode],
    out_to: List[OrientNode],
    amount: Int
)

case class OrientNode(
    out:String,
    in: OrientAccount
)

case class OrientNodeStr(
    out:String
)
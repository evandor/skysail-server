package io.skysail.server.demo.domain

import java.util.Date

import io.skysail.api.ddd.Entity

case class Account(id: Option[String], title: String, initial: Int = 0) extends Entity[String]

case class OrientAccount(
    id: String, 
    title: String, 
    initial: Int
)

case class Transaction(id: Option[String], from: Account, to: Account, date: Date, amout: Int)

case class Pattern(id: Option[String], from: Account, to: Account, amout: Int, accounts: List[Account] = List())//, matcher: (Int) => Boolean)

case class OrientPattern(
    id:String, 
    out_from: List[OrientAccount], 
    out_to: List[OrientAccount], 
    amout: Int
)

case class OrientPattern2(
    id:String, 
    out_from: List[OrientNode], 
    amout: Int
)

case class OrientNode(
    out:String,
    in: OrientAccount
)
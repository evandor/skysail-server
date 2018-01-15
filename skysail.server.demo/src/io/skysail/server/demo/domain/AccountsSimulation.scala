package io.skysail.server.demo.domain

import java.util.Date

import io.skysail.api.ddd.Entity

case class Account(id: Option[String], title: String, initial: Int = 0) extends Entity[String]

case class Transaction(id: Option[String], from: Account, to: Account, date: Date, amout: Int)

case class Pattern(id: Option[String], from: Account, to: Account, amout: Int, accounts: List[Account] = List())//, matcher: (Int) => Boolean)

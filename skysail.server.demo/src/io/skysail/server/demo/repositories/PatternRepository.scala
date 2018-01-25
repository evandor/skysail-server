package io.skysail.server.demo.repositories

import io.skysail.api.persistence.DbService
import io.skysail.domain.model.ApplicationModel
import io.skysail.server.demo.domain.Pattern
import io.skysail.server.demo.domain.OrientPattern
import io.skysail.server.demo.domain.Account

class PatternRepository(dbService: DbService, appModel: ApplicationModel) {

  dbService.createWithSuperClass("V", DbService.tableNameFor(classOf[Pattern]))
  dbService.register(classOf[Pattern])

  def save(entity: Any): String = {
    dbService.persist(entity, appModel)
  }

  def find( /*Filter filter, Pagination pagination*/ ): List[Pattern] = {
    val sql = "SELECT * from " + DbService.tableNameFor(classOf[Pattern])
    //dbService.findGraphs(classOf[Pattern], sql) //, filter.getParams());
    val orientPattern = dbService.findByClass[OrientPattern](classOf[OrientPattern])
    orientPattern.map(op => {
      println("OP: " + op)
      val from = op.out_from.head.in
      val to = "to..." //op.out_to.head.in
      Pattern(
        Some(op.id),
        Account(Some(from.id), from.title, from.initial),
        Account(None, to, 0),
        op.amount)
    }).toList
  }

  def find(id: String): Option[Pattern] = {
    val op = dbService.findById(classOf[OrientPattern], id)
    println("OP: " + op)
    val from = op.out_from.head.in
    val to = op.out_to.head.in
    Some(Pattern(
      Some(op.id),
      Account(Some(from.id), from.title, from.initial),
      Account(Some(to.id), to.title, to.initial),
      op.amount))
  }
}
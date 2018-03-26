package io.skysail.domain.repositories

trait RepositoryApi[T] {

  def find(): List[T]

  def find(id: String): Option[T]

  def save(entity: Any): String

  def delete(id: String): Boolean
}

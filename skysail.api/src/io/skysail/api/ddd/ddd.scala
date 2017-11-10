package io.skysail.api.ddd

/**
  * DDD stands for the concepts of "Domain Driven Design".
  *
  * This sealed trait defines various elements to characterize
  * these concepts.
  */
sealed trait DddElement

/**
  *
  * @tparam T the type of the id attribute for this entity, e.g. Int or String
  */
trait Entity[T] extends DddElement {
  def id: Option[T]

  override def hashCode(): Int = id.hashCode()

  override def equals(obj: scala.Any): Boolean = {
    if (!obj.isInstanceOf[Entity[T]]) {
      return false
    }
    val otherEntity = obj.asInstanceOf[Entity[T]]
    if (id == null || otherEntity.id == null) {
      return false
    }
    if (id.isEmpty || otherEntity.id.isEmpty) {
      return false
    }
    otherEntity.id == id
  }
}

trait ValueObject extends DddElement
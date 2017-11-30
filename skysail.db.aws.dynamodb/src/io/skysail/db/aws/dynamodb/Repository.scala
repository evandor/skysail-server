package io.skysail.db.aws.dynamodb

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient

class Repository(profileName: String, region: String, tableName: String) {

  protected var dbClient: AmazonDynamoDBClient = null

  import awscala._, dynamodbv2._

  implicit val dynamoDB = DynamoDB.at(Region.US_EAST_1)

  val tableMeta: TableMeta = dynamoDB.createTable(
    name = "Members",
    hashPK =  "Id" -> AttributeType.Number,
    rangePK = "Country" -> AttributeType.String,
    otherAttributes = Seq("Company" -> AttributeType.String),
    indexes = Seq(LocalSecondaryIndex(
      name = "CompanyIndex",
      keySchema = Seq(KeySchema("Id", KeyType.Hash), KeySchema("Company", KeyType.Range)),
      projection = Projection(ProjectionType.Include, Seq("Company"))
    ))
  )

  val table: Table = dynamoDB.table("Members").get

  table.put(1, "Japan", "Name" -> "Alice", "Age" -> 23, "Company" -> "Google")
  table.put(2, "U.S.",  "Name" -> "Bob",   "Age" -> 36, "Company" -> "Google")
  table.put(3, "Japan", "Name" -> "Chris", "Age" -> 29, "Company" -> "Amazon")

  val googlers: Seq[Item] = table.scan(Seq("Company" -> cond.eq("Google")))

  table.destroy()

}

package persistance

class MongoDao {
  def write(json: String) = {
    println(s">>>> Persisting post: $json to Mongo")
  }
}

// @ts-check
const Router = require("@koa/router")
const { Firestore } = require("@google-cloud/firestore")
const { collect, withId, withLastUpdated } = require("../utils")

const router = new Router({ prefix: "/vehicles" })

// Create a new client
const firestore = new Firestore()
const collection = firestore.collection("vehicles")

router.get("/", async (ctx) => {
  const snapshot = await collection.get()

  ctx.body = {
    logs: collect(snapshot),
  }
})

router.get("/:id", async (ctx) => {
  const doc = await collection.doc(ctx.params.id).get()
  ctx.assert(doc.exists, 404)
  ctx.body = withLastUpdated(withId(doc))
})

module.exports = router

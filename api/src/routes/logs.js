// @ts-check
const Router = require("@koa/router")
const { Firestore } = require("@google-cloud/firestore")
const { collect, withId, withLastUpdated } = require("../utils")

const router = new Router({ prefix: "/logs" })

// Create a new client
const firestore = new Firestore()
const collection = firestore.collection("logs")

async function hydrateLog(log) {
  const project = await firestore.doc(log.project).get()
  const vehicle = await firestore.doc(log.vehicle).get()

  return {
    ...log,
    project: withLastUpdated(withId(project)),
    timestamp: log.timestamp.seconds,
    vehicle: withLastUpdated(withId(vehicle)),
  }
}

router.get("/", async (ctx) => {
  const snapshot = await collection.get()

  ctx.body = await Promise.all(collect(snapshot).map(hydrateLog))
})

router.get("/:id", async (ctx) => {
  const doc = await collection.doc(ctx.params.id).get()
  ctx.assert(doc.exists, 404)
  ctx.body = await hydrateLog(withId(doc))
})

module.exports = router

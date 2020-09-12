// @ts-check
require("dotenv").config()
const Koa = require("koa")
const Router = require("@koa/router")
const logRoutes = require("./routes/logs")
const projectRouter = require("./routes/projects")
const vehicleRouter = require("./routes/vehicles")

const app = new Koa()
const router = new Router()

router.get("/", (ctx) => {
  ctx.body = "Welcome to the Voyager public API!"
})

app
  .use(router.routes())
  .use(router.allowedMethods())
  .use(logRoutes.routes())
  .use(logRoutes.allowedMethods())
  .use(projectRouter.routes())
  .use(projectRouter.allowedMethods())
  .use(vehicleRouter.routes())
  .use(vehicleRouter.allowedMethods())
  .listen(3000, () => {
    console.log("🚀 Voyager API listening on http://localhost:3000")
  })

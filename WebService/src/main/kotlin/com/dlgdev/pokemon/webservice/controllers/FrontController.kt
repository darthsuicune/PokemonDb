package com.dlgdev.pokemon.webservice.controllers

import com.dlgdev.pokemon.webservice.dagger.DaggerFrontControllerComponent
import com.google.gson.Gson
import java.io.IOException
import java.util.logging.Logger
import javax.inject.Inject
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * The plan is to accept, for now at least, the following kind of requests:
 * /go/dex/<id>
 * /dex/<id> (which is equal to)
 * /mainGames/dex/<id>
 *
 * and to return a JSON formatted answer (for now, maybe more options in the future)
 */
@WebServlet(urlPatterns = arrayOf("/*"))
class FrontController : HttpServlet() {
    @Inject lateinit var logger: Logger
    @Inject lateinit var controllerFactory: ControllerFactory
    @Inject lateinit var gson: Gson

    @Throws(ServletException::class)
    override fun init() {
        super.init()
        val component = DaggerFrontControllerComponent.create()
        component.inject(this)
    }

    @Throws(ServletException::class, IOException::class)
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        try {
            println(req.pathInfo)
            val output = controllerFactory.get(req).process()
            req.session.setAttribute("output", gson.toJson(output.result))
            println("forwarding ${output.result} to ${output.page}")
            req.getRequestDispatcher("/WEB-INF/${output.page}.jsp").forward(req, resp)
        } catch (e: RuntimeException) {

        }
    }
}

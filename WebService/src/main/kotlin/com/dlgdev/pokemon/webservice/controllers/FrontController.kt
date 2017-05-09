package com.dlgdev.pokemon.webservice.controllers

import com.dlgdev.pokemon.webservice.dagger.DaggerFrontControllerComponent
import java.io.IOException
import java.util.logging.Logger
import javax.inject.Inject
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = arrayOf("/*"))
class FrontController : HttpServlet() {
    @Inject lateinit var logger: Logger
    @Inject lateinit var controllerFactory: ControllerFactory

    @Throws(ServletException::class)
    override fun init() {
        super.init()
        val component = DaggerFrontControllerComponent.create()
        component.inject(this)
    }

    @Throws(ServletException::class, IOException::class)
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        controllerFactory.get(req).process()
    }
}

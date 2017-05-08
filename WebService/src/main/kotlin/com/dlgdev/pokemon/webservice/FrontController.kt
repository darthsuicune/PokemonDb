package com.dlgdev.pokemon.webservice

import com.dlgdev.pokemon.database.PokemonProvider
import com.dlgdev.pokemon.webservice.dagger.DaggerFrontControllerComponent
import com.google.gson.Gson
import java.io.IOException
import java.util.logging.Level
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
    @Inject lateinit var provider: PokemonProvider

    @Throws(ServletException::class)
    override fun init() {
        super.init()
        val component = DaggerFrontControllerComponent.create()
        component.inject(this)
    }

    @Throws(ServletException::class, IOException::class)
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        logger.log(Level.ALL, "Testing stuff")
        val pokemon = provider.find(1, 1)
        resp.writer.write(Gson().toJson(pokemon))
    }
}

package com.dlgdev.pokemon.webservice;

import com.dlgdev.pokemon.database.Pokemon;
import com.dlgdev.pokemon.database.PokemonProvider;
import com.dlgdev.pokemon.webservice.dagger.DaggerFrontControllerComponent;
import com.dlgdev.pokemon.webservice.dagger.FrontControllerComponent;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/*")
public class FrontController extends HttpServlet {
	@Inject Logger logger;
	@Inject PokemonProvider provider;

	@Override public void init() throws ServletException {
		super.init();
		FrontControllerComponent component = DaggerFrontControllerComponent.create();
		component.inject(this);
	}

	@Override protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.log(Level.ALL, "Testing stuff");
		Pokemon pokemon = provider.find(1, 0);
		resp.getWriter().write(new Gson().toJson(pokemon));
	}
}

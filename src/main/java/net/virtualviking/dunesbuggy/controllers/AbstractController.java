package net.virtualviking.dunesbuggy.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;

import net.virtualviking.dunesbuggy.vro.VroClient;
import net.virtualviking.dunesbyggy.security.VroToken;

public class AbstractController {

	public AbstractController() {
		super();
	}
	
	@Scope("session")
	protected VroClient getVro() {
		 VroToken auth = (VroToken) SecurityContextHolder.getContext().getAuthentication();
		 try { 
			 return auth.getVraClient();
		 } catch(Exception e) {
			 // Exceptions here are unrecoverable, so throw a RuntimeException
			 //
			 throw new RuntimeException(e);
		 }
	}
}
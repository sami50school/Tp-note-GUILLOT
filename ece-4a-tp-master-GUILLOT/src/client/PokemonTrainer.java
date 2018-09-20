package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import shared.Pokemon;
import shared.Request;

/**
 * This class represents a Pokémon Bank client, which is a Pokémon Trainer.
 * @author strift
 *
 */
public class PokemonTrainer {
	
	public static final String SERVER_HOST = null; // localhost
	public static final int SERVER_PORT = 3000;
	
	/**
	 * The client socket
	 */
	private Socket client;
	
	/**
	 * The client output stream
	 */
	private ObjectOutputStream outputStream;
	
	/**
	 * The client input stream
	 */
	private ObjectInputStream inputStream;
	
	/**
	 * Constructor
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public PokemonTrainer() throws UnknownHostException, IOException {
		/*
		 * TODO
		 * Here you initialize the socket, the input stream, and the output stream
		 */
		client       = new Socket(InetAddress.getLocalHost(), SERVER_PORT);
		inputStream  = new ObjectInputStream(client.getInputStream());
		outputStream = new ObjectOutputStream(client.getOutputStream());
	}
	
	/**
	 * Send a LIST request to the server and read its response
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void getPokemonList() throws IOException, ClassNotFoundException {
		System.out.println("Request: LIST");
		/*
		 * TODO
		 * Here you should write the request to the output stream.
		 */
		outputStream.writeObject(Request.LIST);
		
		this.readResponse();	
	}
	
	/**
	 * Send a STORE request to the server and read its response
	 * @param pokemon
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void sendPokemon(Pokemon pokemon) throws IOException, ClassNotFoundException {
		System.out.println("Request: STORE");
		/*
		 * TODO
		 * Here you should write the request to the output stream, then write the Pokémon to send.
		 */
		outputStream.writeObject(Request.STORE);
        outputStream.flush();
        
		outputStream.writeObject(pokemon);
		outputStream.flush();

		System.out.println("Envoi en cours : " + pokemon);
		this.readResponse();
	}
	
	/**
	 * Send a CLOSE request to the server, read its response, and close everything
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void disconnect() throws IOException, ClassNotFoundException {
		System.out.println("Request: CLOSE");
		/*
		 * TODO
		 * Here you should write the request to the output stream.
		 */
		outputStream.writeObject(Request.CLOSE);
		outputStream.flush();
		
		this.readResponse();
		
		/*
		 * TODO
		 * Here you should close both I/O streams and the socket.
		 */
		
		inputStream.close();
		outputStream.close();
		client.close();
		
	}
	
	/**
	 * Read the response from the server
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void readResponse() throws ClassNotFoundException, IOException {
		/*
		 * TODO
		 * Here you should read the server response from the input stream, then print it.
		 * Note: the server only answers with String ;)
		 */
        System.out.println("Reponse du serveur : ");
        System.out.println(inputStream.readObject());
	}
}

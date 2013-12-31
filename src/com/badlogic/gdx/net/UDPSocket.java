/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.badlogic.gdx.net;

import java.io.IOException;

import com.badlogic.gdx.utils.Disposable;

/**
 * UDPSocket - Class for communicating using the UDP protocol The default
 * implementation uses java.net packages, we also allow you to write your own
 * implementation if you are not happy with it
 * 
 * @author Unkn0wn0ne
 */
public abstract class UDPSocket implements Disposable {

	/**
	 * Sends data to the specified host in the datagram
	 * @param d The datagram containing the data and connection information
	 * @throws IOException If there is an IO error sending the data
	 */
	public abstract void sendData(Datagram d) throws IOException;

	/**
	 * Receives data from the socket
	 * @return A datagram containing the sender information and data
	 * @throws IOException If there is an IO error receiving the data
	 */
	public abstract Datagram receiveData() throws IOException;

	/**
	 * Creates the UDPSocket with the specified configuration.
	 * @param port The port to connect to or listen on
	 * @param hints The configuration hints
	 * @return A UDPSocket that is configured and ready to use.
	 */
	public abstract UDPSocket create(int port, UDPSocketHints hints);
}

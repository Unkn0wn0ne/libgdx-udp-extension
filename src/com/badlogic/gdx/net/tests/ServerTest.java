/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.net.tests;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.net.Packet;
import com.badlogic.gdx.net.Server;
import com.badlogic.gdx.net.UDPSocket;
import com.badlogic.gdx.net.UDPSocketHints;

/** ServerTest.java - A simple server test using the gdx-udp extension.
 * @author Unkn0wn0ne */
public class ServerTest {
	public static void run (int port, UDPSocketHints hints) {
		run(port, hints, null);
	}

	public static void run (int port, UDPSocketHints hints, UDPSocket impl) {
		Server server = null;
		String addr = "";
		if (impl != null) {
			server = new Server(port, hints, impl);
		} else {
			server = new Server(port, hints);
		}

		Packet packet = null;
		try {
			packet = server.recievePacket();
			packet.writeInt(1);
			packet.writeBoolean(true);
			packet.writeBytes("Test".getBytes());
			packet.writeDouble(1.1d);
			packet.writeFloat(2.2f);
			packet.writeShort((short)1);
			packet.writeString("Hello, world!");
			addr = packet.getAddress();
			int i = packet.readInt();
			boolean b = packet.readBoolean();
			byte[] bytes = new byte[4];
			packet.readBytes(bytes);
			double dec = packet.readDouble();
			float f = packet.readFloat();
			short s = packet.readShort();
			String str = packet.readString();
			if (i == 1 && b == true && bytes == "Test".getBytes() && dec == 1.1d && f == 2.2f && s == (short)1
				&& str == "Hello, world!") {
				// All is right with the world
			} else {
				throw new IOException("Data transmission failure. Data did not match!");
			}
		} catch (IOException e) {
			Gdx.app.log("gdx-udp", "IOException while receiving data from client in server test: ", e);
		}

		if (packet == null) {
			packet = new Packet();
		}
		try {
			packet.writeBoolean(true);
			server.sendPacket(packet, addr);
		} catch (IOException e) {
			Gdx.app.log("gdx-udp", "IOException while sending data to client in server test: ", e);
		}

		// Now, we dispose all the variables
		server.dispose();
		packet.dispose();
	}
}

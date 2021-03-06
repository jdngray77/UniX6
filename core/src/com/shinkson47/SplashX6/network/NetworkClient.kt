/*░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
 ░ FOSS 2022. The Splash Project.                                                                                                                                                 ░
 ░ https://www.shinkson47.in/SplashX6                                                                                                                                             ░
 ░ Jordan T. Gray.                                                                                                                                                                ░
 ░                                                                                                                                                                                ░
 ░                                                                                                                                                                                ░
 ░                                                                                                                                                                                ░
 ░           _____                    _____                    _____            _____                    _____                    _____                                           ░
 ░          /\    \                  /\    \                  /\    \          /\    \                  /\    \                  /\    \                         ______           ░
 ░         /::\    \                /::\    \                /::\____\        /::\    \                /::\    \                /::\____\                       |::|   |          ░
 ░        /::::\    \              /::::\    \              /:::/    /       /::::\    \              /::::\    \              /:::/    /                       |::|   |          ░
 ░       /::::::\    \            /::::::\    \            /:::/    /       /::::::\    \            /::::::\    \            /:::/    /                        |::|   |          ░
 ░      /:::/\:::\    \          /:::/\:::\    \          /:::/    /       /:::/\:::\    \          /:::/\:::\    \          /:::/    /                         |::|   |          ░
 ░     /:::/__\:::\    \        /:::/__\:::\    \        /:::/    /       /:::/__\:::\    \        /:::/__\:::\    \        /:::/____/                          |::|   |          ░
 ░     \:::\   \:::\    \      /::::\   \:::\    \      /:::/    /       /::::\   \:::\    \       \:::\   \:::\    \      /::::\    \                          |::|   |          ░
 ░   ___\:::\   \:::\    \    /::::::\   \:::\    \    /:::/    /       /::::::\   \:::\    \    ___\:::\   \:::\    \    /::::::\    \   _____                 |::|   |          ░
 ░  /\   \:::\   \:::\    \  /:::/\:::\   \:::\____\  /:::/    /       /:::/\:::\   \:::\    \  /\   \:::\   \:::\    \  /:::/\:::\    \ /\    \          ______|::|___|___ ____  ░
 ░ /::\   \:::\   \:::\____\/:::/  \:::\   \:::|    |/:::/____/       /:::/  \:::\   \:::\____\/::\   \:::\   \:::\____\/:::/  \:::\    /::\____\        |:::::::::::::::::|    | ░
 ░ \:::\   \:::\   \::/    /\::/    \:::\  /:::|____|\:::\    \       \::/    \:::\  /:::/    /\:::\   \:::\   \::/    /\::/    \:::\  /:::/    /        |:::::::::::::::::|____| ░
 ░  \:::\   \:::\   \/____/  \/_____/\:::\/:::/    /  \:::\    \       \/____/ \:::\/:::/    /  \:::\   \:::\   \/____/  \/____/ \:::\/:::/    /          ~~~~~~|::|~~~|~~~       ░
 ░   \:::\   \:::\    \               \::::::/    /    \:::\    \               \::::::/    /    \:::\   \:::\    \               \::::::/    /                 |::|   |          ░
 ░    \:::\   \:::\____\               \::::/    /      \:::\    \               \::::/    /      \:::\   \:::\____\               \::::/    /                  |::|   |          ░
 ░     \:::\  /:::/    /                \::/____/        \:::\    \              /:::/    /        \:::\  /:::/    /               /:::/    /                   |::|   |          ░
 ░      \:::\/:::/    /                  ~~               \:::\    \            /:::/    /          \:::\/:::/    /               /:::/    /                    |::|   |          ░
 ░       \::::::/    /                                     \:::\    \          /:::/    /            \::::::/    /               /:::/    /                     |::|   |          ░
 ░        \::::/    /                                       \:::\____\        /:::/    /              \::::/    /               /:::/    /                      |::|   |          ░
 ░         \::/    /                                         \::/    /        \::/    /                \::/    /                \::/    /                       |::|___|          ░
 ░          \/____/                                           \/____/          \/____/                  \/____/                  \/____/                         ~~               ░
 ░                                                                                                                                                                                ░
 ░                                                                                                                                                                                ░
 ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░*/

package com.shinkson47.SplashX6.network

import com.badlogic.gdx.Gdx
import com.shinkson47.SplashX6.game.Hypervisor
import com.shinkson47.SplashX6.game.Hypervisor.update
import com.shinkson47.SplashX6.utility.Assets
import com.shinkson47.SplashX6.utility.Utility.warnDev
import com.shinkson47.SplashX6.utility.debug.Console
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.StreamCorruptedException
import java.lang.Exception
import java.net.Socket

/**
 * # A socket allowing this client to connect to a hosting client.
 */
object NetworkClient {
    var socket : Socket? = null
    lateinit var _clientInput  : ObjectInputStream
    lateinit var _clientOutput : ObjectOutputStream
    private lateinit var socketListener: NetworkClientListener

    var hasStarted: Boolean = false

    var lastState: Packet? = null
        private set

    fun disconnect() {
        socketListener.host.interrupt()
        socketListener.host.stop()
    }

    fun connect() {
        hasStarted = false
        socket = Socket("192.168.1.167",25565)
        _clientOutput = ObjectOutputStream(socket!!.getOutputStream())
        _clientInput  = ObjectInputStream (socket!!.getInputStream())
                //TODO
        //GameData.networkSet(status.gameState!!)

        socketListener = NetworkClientListener()
        val thread = Thread(socketListener)
        socketListener.host = thread.apply { start() }
    }

    fun resetConnection() {
        println("Connection reset.")
        disconnect()
        connect()
    }


    fun postUpdate () {
        statusUpdate(read())
    }

    private class NetworkClientListener() : Runnable {
        lateinit var host : Thread
        override fun run() {
            println("Client alive")
            while (!this::host.isInitialized);
            while (!host.isInterrupted) {
                try {
                    val pkt = read()
                    println("Client (${Thread.currentThread().name}) recieved : $pkt")
                    when (pkt.type) {
                        PacketType.Ping -> send(Packet(PacketType.Pong))
                        PacketType.Pong -> warnDev("The server sent the client a random pong?")
                        PacketType.Ack -> warnDev("The server sent the client a random ack?")
                        PacketType.Status -> statusUpdate(pkt)
                        PacketType.Start -> statusUpdate(pkt)
                        PacketType.End -> TODO("The client doesn't know how to respond to the server.")
                        PacketType.Disconnect -> {
                            Gdx.app.postRunnable { Hypervisor.endGame() }; send(Packet(PacketType.Ack)); disconnect()
                        }
                        PacketType.Identify -> send(Packet(PacketType.Identify, data = Assets.REF_PREFERENCES.getString("USER_NAME")))
                    }
                }catch (e: Exception) {
                    println("Client packet failure")
                    e.printStackTrace()
                }
            }
            println("Client dead")
        }
    }

    fun send(packet: Packet) = Packet.send(packet, _clientInput, _clientOutput)
    fun read() : Packet {
        while (true) {
            try {
                return _clientInput.readObject() as Packet
            } catch (e: Exception) {
                if (e is StreamCorruptedException)
                    resetConnection();
                println("Failed to read a packet. Requesting resend. ${e.message}")
                send (Packet(PacketType.Resend))
            }
            send(Packet(PacketType.Ack))
            // TODO implement a max retry count.
        }
    }

    fun isConnected() : Boolean = socket?.isConnected == true

    fun statusUpdate(pkt : Packet) {
        lastState = pkt
        if (pkt.gameState == null) {
            warnDev("Received an state update from server that contained no state!")
            return
        }

        with (lastState!!.gameState!!) {
            this.currentPlayerIndex = pkt.data as Int
            if (hasStarted)
                Gdx.app.postRunnable { Hypervisor.update(this) }
            else if (pkt.type == PacketType.Start) {
                hasStarted = true
                Gdx.app.postRunnable { Hypervisor.load(this); }
            }
        }

        send(Packet(PacketType.Ack, null))
    }


    @JvmStatic
    fun main(args: Array<String>) {
        connect()
    }
}
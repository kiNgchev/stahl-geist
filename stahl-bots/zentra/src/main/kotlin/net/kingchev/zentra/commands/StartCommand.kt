package net.kingchev.zentra.commands

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.types.User

@CommandHandler(["/start"])
public suspend fun start(user: User, client: TelegramBot) {
    message { "hello" }
        .send(user, client)
}

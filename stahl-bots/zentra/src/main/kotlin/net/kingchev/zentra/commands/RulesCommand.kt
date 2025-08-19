package net.kingchev.zentra.commands

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.types.User

private const val RULES = """
1. No spam
2. No NSFW
3. No toxic
4. No python
"""

@CommandHandler(["/rules"])
public suspend fun rulesCommand(chat: User, client: TelegramBot) {
    message { RULES }
        .send(chat, client)
}
package net.kingchev.zentra.telegram

import eu.vendeli.tgbot.interfaces.ctx.ClassManager
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
public class ZentraClassManager(private val context: ApplicationContext) : ClassManager {
    override fun getInstance(kClass: KClass<*>, vararg initParams: Any?): Any {
        kClass.objectInstance?.also { return it }
        return context.getBean(kClass.java, *initParams)
    }
}
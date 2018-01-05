package com.medicalsystem.util

import org.apache.log4j.Logger

fun <T : Any> T.logger(): Logger = Logger.getLogger(this.javaClass)
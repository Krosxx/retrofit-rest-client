package cn.vove7.plugin.rest.filetype

import com.intellij.openapi.fileTypes.FileTypeFactory
import com.intellij.openapi.fileTypes.FileTypeConsumer
import cn.vove7.plugin.rest.filetype.RestFileType

/**
 *
 */
class RestFileTypeFactory : FileTypeFactory() {
    override fun createFileTypes(consumer: FileTypeConsumer) {
        consumer.consume(RestFileType.INSTANCE, "rest")
    }
}
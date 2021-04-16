package utils

import java.io.ByteArrayOutputStream
import java.util.zip.Deflater
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * Classe responsável por compactar um lista de arquivos.
 *
 * @param compressionLevel Int - Define o nível de compressão do arquivo.
 * @see Deflater
 * */
class CompactFile(compressionLevel: Int = Deflater.DEFAULT_COMPRESSION) {

    private val baos = ByteArrayOutputStream()
    private val zos: ZipOutputStream

    init {
        zos = ZipOutputStream(baos)
        zos.setLevel(compressionLevel)
    }

    /**
     * Método resposável com criar o arquivo zip
     *
     * @param files List<ByteArray> - Lista de arquivos
     * @param filenames List<String> - Lista de nomes dos arquivos com extensão. Ex.: texto.txt
     * */
    fun criarArquivoZip(files: List<ByteArray>, filenames: List<String>): ByteArray {

        if (files.size != filenames.size) {
            throw ArrayIndexOutOfBoundsException("Tamanho da lista de arquivos e da lista de nomes diferentes.")
        }

        for (index in files.indices) {
            val entry = ZipEntry(filenames[index])
            val file = files[index]
            entry.size = file.size.toLong()
            zos.putNextEntry(entry)
            zos.write(file)
            zos.closeEntry()
        }
        zos.close()
        return baos.toByteArray()
    }
}

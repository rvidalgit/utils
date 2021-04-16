package utils

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.ImageType
import org.apache.pdfbox.rendering.PDFRenderer
import org.apache.pdfbox.tools.imageio.ImageIOUtil
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.imageio.ImageIO

/**
 * Classe CreateThumbnails
 *
 *  implementation group: 'org.apache.pdfbox', name: 'pdfbox', version: '2.0.22'
 *  implementation group: 'org.apache.pdfbox', name: 'pdfbox-tools', version: '2.0.22'
 * */
class CreateThumbnails {

    /**
     * Metódo responsável por transformar a primeira página do documento PDF em um jpg para preview.
     *
     * @param conteudo ByteArray - Conteúdo do arquivo
     * @param
     * */
    fun pdfToImage(conteudo: ByteArray): ByteArray {
        val document = PDDocument.load(ByteArrayInputStream(conteudo)) //carrega o documento PDF
        val pdfRenderer = PDFRenderer(document) //rederiza o PDF para manipulação
        val bufferedImage = pdfRenderer.renderImage(0, 0.6f, ImageType.RGB) //cria a imagem do PDF
        document.close()
        val baos = ByteArrayOutputStream()
        ImageIOUtil.writeImage(bufferedImage, "jpg", baos, 300, 0.7f) //grava a imagem no baos
        //ImageIO.write(bufferedImage, "jpg", bos)
        return baos.toByteArray()
    }

    /*fun image(conteudo: ByteArray): ByteArray {
        val imageIn = ImageIO.read(ByteArrayInputStream(conteudo))
        val baos = ByteArrayOutputStream()
        ImageIOUtil.writeImage(imageIn, "jpg", baos, 300, 0.7f)
        return baos.toByteArray()
    }*/

    /**
     * Redimenciona uma imagem
     *
     * @param conteudo ByteArray - Arquivo a ser redimencionado
     * @param format String - Formato do arquivo
     * @param ratio Double - Taxa de proporção para redução
     * @return ByteArray - Arquivo redimencionado
     * */
    fun image(conteudo: ByteArray, format: String, ratio: Double = 0.5): ByteArray {
        val input: InputStream = ByteArrayInputStream(conteudo)
        val imageIn = ImageIO.read(input)
        val imageOut = scale(imageIn, ratio, imageIn.type)
        val baos = ByteArrayOutputStream()
        ImageIO.write(imageOut, format, baos)
        return baos.toByteArray()
    }

    /**
     * Redimenciona a imagem de acordo com a taxa de proporção passada
     *
     * @param source BufferedImage - Arquiva de imagem
     * @param ratio Double - Taxa de proporção
     * @param type Int - Tipo da imagem
     * @see BufferedImage.imageType
     * @return BufferedImage
     * */
    private fun scale(source: BufferedImage, ratio: Double, type: Int): BufferedImage {
        val w = (source.width * ratio).toInt()
        val h = (source.height * ratio).toInt()
        val bi = BufferedImage(w, h, type)
        val g2d = bi.createGraphics()
        val xScale = w.toDouble() / source.width
        val yScale = h.toDouble() / source.height
        val at = AffineTransform.getScaleInstance(xScale, yScale)
        g2d.drawRenderedImage(source, at)
        g2d.dispose()
        return bi
    }
}

package jp.co.eb.fliggy.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import jp.co.eb.fliggy.converter.Result;
import jp.co.eb.fliggy.enums.ResultEnum;
import jp.co.eb.fliggy.exception.ParamNotValidException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.io.Writer;
import java.util.stream.Collectors;

@RestControllerAdvice("jp.co.eb.fliggy.api")
@Slf4j
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ ParamNotValidException.class })
    public String ParamNotValidExceptionHandler(ParamNotValidException e) {
        log.error("参数错误。({})", e.getMessage());
        return beanToXml(new Result<>(ResultEnum.PARA_ERROR));
    }


    public static <T> T xmlToObject(String xml,Class<T> clazz) {
        try {
            // throws IOException, XMLStreamException
            XmlMapper xmlMapper = new XmlMapper();
            // 字段为null，自动忽略，不再序列化
            xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            // 添加头信息
            xmlMapper.configure( ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
            xmlMapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);

            return xmlMapper.readValue(xml, clazz);
        } catch (JsonProcessingException e) {
            log.error("xmlToObject error:", e.getMessage());
        }
        return null;
    }

    public static String beanToXml(Object object) {
        try {
            // throws IOException, XMLStreamException
            XmlMapper xmlMapper = new XmlMapper();
            // 字段为null，自动忽略，不再序列化
            xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            // 添加头信息
//            xmlMapper.configure( ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
//            xmlMapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);

            return xmlMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("beanToXml error:", e.getMessage());
        }
        return null;
    }


//    private static String beanToXml(Object object)  {
//
//        JAXBContext context = null;
//        try {
//            context = JAXBContext.newInstance(object.getClass());
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            Writer writer = new StringWriter();
//            marshaller.marshal(object, writer);
//            return writer.toString();
//        } catch (JAXBException e) {
////            throw new RuntimeException(e);
//            log.error("beanToXml error:", e.getMessage());
//        }
//        return null;
//
//    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        log.debug("body:{}", body);
        if (body instanceof Result) {
            return body;
        }
        return new Result<>(body);
    }
}

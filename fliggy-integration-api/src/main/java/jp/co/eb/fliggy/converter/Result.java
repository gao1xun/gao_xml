package jp.co.eb.fliggy.converter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.*;
import jp.co.eb.fliggy.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "ResultResponse")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -8943347414816765349L;
    private Boolean success;
    private String code;
    private String message;

    @JacksonXmlText
    private T data;

    public Result(T data) {
        this(ResultEnum.SUCCESS);
        this.data = data;
    }

    public Result(ResultEnum resultEnum) {
        this.success = resultEnum.getSuccess();
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }
}

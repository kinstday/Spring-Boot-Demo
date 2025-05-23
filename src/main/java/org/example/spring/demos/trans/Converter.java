package org.example.spring.demos.trans;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class Converter {
//    public static Converter INSTANT = Mappers.getMapper(Converter.class);

    /**
     * extra字段解析后的buffer，避免多次重复解析
     */
    private final ThreadLocal<String[]> extraFieldBufferLocal = new ThreadLocal<>();


    // 字段类型映射修改
    @Mapping(source = "age", target = "age", resultType = Integer.class)
    // 字段名映射修改
    @Mapping(source = "userNick", target = "nick")
    public abstract Target convert(Source source);

    @Mapping(source = "vo",target = "extra",qualifiedByName = "convertToExtra")
    public abstract DTO convert(VO vo);

    @Named("convertToExtra")
    public String convertToExtra(VO vo) {
        return String.format("%s,%s", vo.getAge(), vo.getUserNick());
    }

    @Mapping(target = "age", source = "extra", qualifiedByName = "extractAge")
    @Mapping(target = "userNick", source = "extra", qualifiedByName = "extractUserNick")
    public abstract VO convert(DTO dto);

    @Named("extractAge")
    public Long extractAge(String extra) {
        // 从extra中提取第一个值
        if (extraFieldBufferLocal.get() == null) {
            extraFieldBufferLocal.set(extractExtraField(extra));
        }

        return Long.valueOf(extraFieldBufferLocal.get()[0]);
    }

    @Named("extractUserNick")
    public String extractUserNick(String extra) {
        if (extraFieldBufferLocal.get() == null) {
            extraFieldBufferLocal.set(extractExtraField(extra));
        }

        return extraFieldBufferLocal.get()[1];
    }

    /**
     * 提取extra字段
     *
     * @param extra extra字段
     * @return extra字段的提取结果
     */
    public String[] extractExtraField(final String extra) {
        return extra.split(",");
    }
}
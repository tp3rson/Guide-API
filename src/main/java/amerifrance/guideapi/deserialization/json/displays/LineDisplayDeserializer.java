package amerifrance.guideapi.deserialization.json.displays;

import amerifrance.guideapi.api.TextProvider;
import amerifrance.guideapi.deserialization.json.JsonDeserializer;
import amerifrance.guideapi.api.RegisterDeserializer;
import amerifrance.guideapi.displays.LineDisplay;

@RegisterDeserializer("LINE_DISPLAY")
public class LineDisplayDeserializer implements JsonDeserializer {

    @Override
    public Object deserialize(String value, Object... initParameters) {
        return new LineDisplay((TextProvider) initParameters[0]);
    }
}
package de.htwberlin.prog2.datamodel;

/**
 * Created by laura on 08.02.17.
 */
public class NodeData {

    private static final int MAX_CHARS = 3;

    private String content;

    public NodeData(String content) {
        setContent(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content.length() > MAX_CHARS) {
            this.content = content.substring(0, 3);
        } else {
            this.content = content;
        }
    }

    @Override
    public String toString() {
        return "NodeData{" +
                "content='" + content + '\'' +
                '}';
    }

}

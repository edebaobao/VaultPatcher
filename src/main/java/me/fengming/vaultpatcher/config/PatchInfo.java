package me.fengming.vaultpatcher.config;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;

public class PatchInfo {
    private String name;
    private String desc;
    private String mods;
    private String authors;

    public String getName() {
        return name == null || name.isEmpty() ? "Unknown Full File Name" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc == null || desc.isEmpty() ? "No Description" : desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMods() {
        return mods == null || mods.isEmpty() ? "Unknown Translate Mod Name" : mods;
    }

    public void setMods(String mods) {
        this.mods = mods;
    }

    public String getAuthors() {
        return authors == null || authors.isEmpty() ? "Unknown Author Name" : authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }


    public void readJson(JsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.peek() != JsonToken.END_OBJECT) {
            switch (reader.nextName()) {
                case "name" -> setName(reader.nextString());
                case "desc" -> setDesc(reader.nextString());
                case "mods" -> setMods(reader.nextString());
                case "authors" -> setAuthors(reader.nextString());
                default -> reader.skipValue();
            }
        }
        reader.endObject();
    }

    public void writeJson(JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("name").value(getName());
        writer.name("desc").value(getDesc());
        writer.name("mods").value(getMods());
        writer.name("authors").value(getAuthors());
        writer.endObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PatchInfo that = (PatchInfo) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .append(desc, that.desc)
                .append(mods, that.mods)
                .append(authors, that.authors)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(23, 53)
                .append(name)
                .append(desc)
                .append(mods)
                .append(authors)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("desc", desc)
                .append("mods", mods)
                .append("authors", authors)
                .toString();
    }
}

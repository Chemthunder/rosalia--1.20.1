package silly.chemthunder.rosalia.index;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import silly.chemthunder.rosalia.Rosalia;

import java.util.LinkedHashMap;
import java.util.Map;

public interface RosaliaSounds {
    Map<SoundEvent, Identifier> SOUND_EVENTS = new LinkedHashMap<>();

    SoundEvent ITEM_LONGSWORD_SPREAD = createSoundEvent("item.longsword_spread");

    SoundEvent MUSIC_DISC_DAISY_BELL = createSoundEvent("music.daisy_bell");

    static void index() {
        SOUND_EVENTS.keySet().forEach(soundEvent -> Registry.register(Registries.SOUND_EVENT, SOUND_EVENTS.get(soundEvent), soundEvent));
    }

    private static SoundEvent createSoundEvent(String path) {
        SoundEvent soundEvent = SoundEvent.of(new Identifier(Rosalia.MOD_ID, path));
        SOUND_EVENTS.put(soundEvent, new Identifier(Rosalia.MOD_ID, path));
        return soundEvent;
    }
}

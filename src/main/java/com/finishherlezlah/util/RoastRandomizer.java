package com.finishherlezlah.util;

import com.finishherlezlah.config.RoastConfig;
import com.finishherlezlah.error.RoastExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// import java.util.* -> Possible vulnerability with multiple objects, data corruption

// You are explicitly stating the type of object you want
import java.util.*;


@Component
public class RoastRandomizer {

    private final RoastConfig roastConfig;

    @Autowired
    public RoastRandomizer(RoastConfig roastConfig) {
        this.roastConfig = roastConfig;
    }


    /**
     * Returns a roast for a given category and index.
     * Throws if category is invalid or index is out of bounds.
     * Direct index roast (used for future admin tools, testing, or legacy access)
     */

    public static String getRoast(String category, int index) {
        List<String> roasts = roastMap.get(category.toUpperCase());

        if (roasts == null || index < 0 || index >= roasts.size()) {
            throw new RoastExceptionHandler(category, index);
        }
        return roasts.get(index);
    }

    private static final Logger log = LoggerFactory.getLogger(RoastRandomizer.class);

    // intelligent state management system

    public static String getNextRoast(String category) {
        category = category.toUpperCase();
        List<String> allRoasts = roastMap.get(category);

        if (allRoasts == null || allRoasts.isEmpty()) {
            throw new RoastExceptionHandler(category, -1);
        }

        // Initialize cache set for category
        roastCache.putIfAbsent(category, new HashSet<>());
        Set<String> usedRoasts = roastCache.get(category);

        log.info("💾 [CACHE BEFORE] Category [{}] used roasts: {}", category, usedRoasts);

        // Reset when all roasts have been used
        if (usedRoasts.size() == allRoasts.size()) {
            log.info("♻️ [RESET] All roasts used for category [{}]. Resetting cache.", category);
            usedRoasts.clear();
        }

        List<String> availableRoasts = allRoasts.stream()
                .filter(r -> !usedRoasts.contains(r))
                .toList();

        String selectedRoast = availableRoasts.get(new Random().nextInt(availableRoasts.size()));
        usedRoasts.add(selectedRoast);

        log.info("🔥 [SELECTED] Lezlah served: {}", selectedRoast);
        log.info("💾 [CACHE AFTER] Category [{}] now contains: {}\n", category, usedRoasts);

        return selectedRoast;
    }


    private static final Map<String, Set<String>> roastCache = new HashMap<>();

    private static final Map<String, List<String>> roastMap = new HashMap<>();

   static {
       roastMap.put("LEZLAHSAYS", List.of(
               "Oh, you tried me? Girl, I will invalidate your entire character arc with one GET request.",
               "You smell like you never passed a rate limit test. Sit down.",
               "I debug souls. Don’t make me step into your logs.",
               "I’m not mad. I’m just returning your energy... with enhancements.",
               "Tell your code to stop calling me—I don’t respond to insecure functions.",
               "I’m not the villain. I’m the exception your weak validation couldn’t catch.",
               "You should've 403’d yourself before speaking to me.",
               "Lezlah doesn’t crash. She *shuts down the system*—gracefully, of course.",
               "I'm the reason your API has trust issues.",
               "You’re not built for me. But thanks for beta testing.",
               "It's Lezlah 🤬itch!",
               "Sweetie, don’t touch me. You weren’t even authorized.",
               "You're built like a deprecated method—outdated and insecure.",
               "Even my 404s return more value than you.",
               "Talk to me nice or get nullPointer’d.",
               "You’re trying to POST where I only accept GET. Cute.",
               "She doesn’t need validation… she IS the schema.",
               "I don’t crash. I decide when the app closes."

       ));
   }


    static {
        roastMap.put("FOREHEAD", List.of(
                "Your forehead is shaped like an almond.",
                "How did your forehead get ultra HD resolution but your life choices look like a 2003 YouTube video?",
                "Her baby hairs out here struggling, talking about: 'We tried to deliver your edges, but the forehead was too far away.'",
                "If I swipe down on her forehead, am I getting another chapter or nah?",
                "Sis got a buffering animation above her eyebrows. 'Estimated time remaining: Too damn long.'",
                "That forehead got bookmarks. Plotlines. Spoilers. And somehow, you still don’t know how the story ends.",
                "Her forehead be catching more light than her personality.",
                "You could project the whole PowerPoint presentation on that forehead and still have room for Q&A.",
                "That forehead got a 3-day weather forecast built-in. Swipe up for alerts.",
                "When she walks in, her forehead enter the chat 5 seconds before the rest of her.",
                "Her forehead got its own zip code."
        ));

        roastMap.put("LIKETOHATE", List.of(
                "Sis, talking about 'Haters watching'—nah, most got you muted.",
                "At this point, you should put 'Professional L' in your bio instead.",
                "LoserBraids -> 'The one they love to hate 🤭' DIRECTOR: CUT! CUT! CUT! " +
                        "Remember—humility. Let’s go again!",
                "The only thing we hating on, is that you like to twist lies.",
                "Sis got the personality depth of a parking lot.",
                "You're the reason therapy raised its prices"
        ));

        roastMap.put("FAKEQUEEN", List.of(
                "She posts 'unbothered & blessed' while crying over her ex's new post.",
                "'I’m not like other girls.' Sis, you’re just another remix with the same tired captions.",
                "'It’s giving ICONIC'—no, it’s giving delusion.",
                "Mysterious until you realize... it's just empty sass and overpriced lip gloss.",
                "She look like she says 'good morning' with attitude just to start the argument first.",
                "She wearing that red jacket like she the final boss of a Target aisle brawl.",
                "She walk into the salon like, ‘Make me look like I judge silently and aggressively.’ " +
                        "Mission accomplished.",
                "Edges tight. Expression tighter. She look like she just dared someone to ask 'Who braided this?'",
                "Why she look like Medusa's wannabe little sister with that stare? " +
                        "On second thought, I want to turn into stone....#StoneVibesOnly",
                "She looking like that because her doordash order is 10 seconds late.",
                "She already decided the food cold even though it hasn’t made it out the car yet.",
                "She brought no pen, no paper, just energy and expensive moisturizer.",
                "She RSVP’d to her own wedding just to make sure she shows up looking better than her clone.",
                "Both versions of her gonna cheat on you, and then get mad you found out.",
                "She dressed like a backup dancer in Destiny’s Child’s ‘Survivor’ but only rehearsed the attitude."
        ));

        roastMap.put("WANNABESTRAIGHT", List.of(
                "A man: 'You look nice.' Her: 'Thanks.' A woman: 'You look nice.' Her: 'The sun rises and sets in your eyes.'",
                "'I haven’t kissed her yet.' Sis, you’re just waiting for the sequel.",
                "You’re dreaming in lesbian 4K and calling it a glitch.",
                "One more emoji and they’d have to change their relationship status to ‘pending in public.",
                "“Your beauty, your radiance <3” is the digital equivalent of " +
                        "dropping a rose at her feet and whispering in cursive. \uD83D\uDC80",
                "She calls you 'bestie' but y’all cuddle like a prequel.",
                "Sis said 'I’m straight' then laughed too hard at your joke and touched your thigh. Aight.",
                "\uD83C\uDFA4✨ My loneliness... is killing me... and I... must deny these feelings for her even though we basically shared a soul tie at Applebee’s.",
                "Every time she hugs you, your brain whispers, 'Oops!... I caught feelings again.'",
                "She posts a thirst trap and you're like, 'My loneliness... is gaslighting me.'",
                "Oops, you did it again—you stared too long, giggled too soft, and now she thinks you’re proposing with your eyes.",
                "Girl you should have not stolen Britney Spears line -> 'Oops I did it again, I played with your heart!' Britney Spears is offended."
        ));

        roastMap.put("LOSERBRAIDKNUCKS", List.of(
                "Looking like Knuckles the Echidna: 'I must protect the Master Emerald!'",
                "She’s one bad comment away from throwing hands in 4K.",
                "Girl, just tell me where the gas station is. You’re not in Skyrim.",
                "Why she look like she just told Sonic, 'You can’t run from this smoke.'",
                "Never knew Knuckles the Echidna had a sister… and she don’t play.,",
                "Why she look like she hopes the Master Emerald is okay?",
                "She look like she just finished arguing with Sonic and went straight to Walgreens.",
                "Knuckles? Nah, that's Buckles — strapped up in emotion and ready to square up.",
                "Not Knuckles' sister showing up late to team meetings again talkin’ bout ‘I had to vibe check some chaos energy.’",
                "Sis really woke up and chose Echidna Elegance™ — boots tight, stance locked, no emerald left behind."

        ));
    }
}

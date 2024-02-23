package com.example.sunshineserver.quest.infrastructure;

import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.QuestionType;
import com.example.sunshineserver.quest.domain.StatInfo;
import com.example.sunshineserver.quest.domain.repository.QuestTemplateRepository;
import com.example.sunshineserver.user.domain.ExperiencePoint;
import com.example.sunshineserver.user.domain.StatType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class QuestInitializer {

    private final QuestTemplateRepository questTemplateRepository;

    @PostConstruct
    @Transactional
    public void initialize() {
        questTemplateRepository.save(new QuestTemplate(1, "Meditate for 5 Minutes",
            "Spend 5 minutes in meditation to calm your mind.",
            ExperiencePoint.from(10), QuestionType.TIMER,
            StatInfo.of(StatType.STR, 1), 1));

        questTemplateRepository.save(new QuestTemplate(2, "Gaze at Distant Mountains",
            "Relieve your eye strain by looking at distant scenery.",
            ExperiencePoint.from(10), QuestionType.DAILY,
            StatInfo.of(StatType.SPI, 3), 1));

        questTemplateRepository.save(new QuestTemplate(3, "Take a Picture of the Sky",
            "Capture the beauty of the sky in a photograph.",
            ExperiencePoint.from(10), QuestionType.PHOTO,
            StatInfo.of(StatType.SPI, 3), 1));

        questTemplateRepository.save(new QuestTemplate(4, "Wear Your Favorite Color",
            "Dress in clothes of your favorite color and take a picture.",
            ExperiencePoint.from(10), QuestionType.PHOTO,
            StatInfo.of(StatType.PEA, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(5, "Read 10 Pages of a Book", "Read 10 pages to gain new knowledge.",
	ExperiencePoint.from(10), QuestionType.PHOTO,
	StatInfo.of(StatType.KNO, 5), 1));

        questTemplateRepository.save(new QuestTemplate(6, "Bask in the Sunlight",
            "Open a window and soak up the sunlight for 10 minutes.",
            ExperiencePoint.from(10), QuestionType.TIMER,
            StatInfo.of(StatType.SPI, 2), 1));

        questTemplateRepository.save(new QuestTemplate(7, "Groom Yourself",
            "Engage in self-care activities and document the process or result.",
            ExperiencePoint.from(10), QuestionType.PHOTO,
            StatInfo.of(StatType.STR, 3), 1));

        questTemplateRepository.save(new QuestTemplate(8, "Eat Your Favorite Food",
            "Enjoy your favorite food and capture the moment.",
            ExperiencePoint.from(10), QuestionType.PHOTO,
            StatInfo.of(StatType.SPI, 3), 1));

        questTemplateRepository.save(new QuestTemplate(9, "Take a 15-minute Walk",
            "Walk outdoors for at least 15 minutes to breathe fresh air.",
            ExperiencePoint.from(20), QuestionType.PHOTO,
            StatInfo.of(StatType.PEA, 5), 2));

        questTemplateRepository.save(new QuestTemplate(10, "Reflect on a Quote",
            "Write down a memorable quote from a book or movie and explain why it stood out to you.",
            ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
            StatInfo.of(StatType.KNO, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(11, "Think of a dish you want to make",
	"Think of a dish you want to make and briefly write down the reason or recipe.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.PEA, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(12, "Think of a favorite song",
	"Think of a favorite song and write down why you like it.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(13, "Order coffee at Coffee Shop",
	"Go to Coffee Shop, order your favorite coffee, and take a picture of the ordered coffee.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.PEA, 7), 2));

        questTemplateRepository.save(
            new QuestTemplate(14, "Organize your pencil case",
	"Organize your pencil case and take pictures before and after organizing to compare.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.KNO, 2), 1));

        questTemplateRepository.save(new QuestTemplate(15, "Contact a friend or acquaintance",
            "Send a message asking about their well-being to a friend or acquaintance and take a screenshot of the conversation.",
            ExperiencePoint.from(20), QuestionType.PHOTO,
            StatInfo.of(StatType.PEA, 7), 1));

        questTemplateRepository.save(
            new QuestTemplate(16, "Enjoy your favorite dessert",
	"Enjoy your favorite dessert and take a picture of that moment.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(17, "Dance to an upbeat song",
	"Dance to an upbeat song for 15 minutes. Use a timer to measure the time.",
	ExperiencePoint.from(20), QuestionType.TIMER,
	StatInfo.of(StatType.KNO, 7), 1));

        questTemplateRepository.save(
            new QuestTemplate(18, "Express emotions/thoughts with music",
	"Write down the emotions or thoughts you felt that day along with music.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 4), 1));

        questTemplateRepository.save(
            new QuestTemplate(19, "Practice vocalization",
	"Practice vocalization for 15 minutes. Use a timer to measure the time.",
	ExperiencePoint.from(20), QuestionType.TIMER,
	StatInfo.of(StatType.PEA, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(20, "Summarize your day in one line",
	"Summarize your day in one line.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(21, "Grocery shopping at the supermarket",
	"Go grocery shopping at the supermarket. How about purchasing ingredients for your favorite foods?",
	ExperiencePoint.from(21), QuestionType.PHOTO,
	StatInfo.of(StatType.KNO, 7), 1));

        questTemplateRepository.save(
            new QuestTemplate(22, "Try cooking something simple", "Try cooking something simple.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.KNO, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(23, "Take a walk", "Take a walk and take a photo.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.STR, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(24, "Write about a phrase that empowered you in your life",
	"Write about a phrase that empowered you in your life.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(25, "Take the MBTI test", "Take the MBTI test and take a photo.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.KNO, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(26, "Watch a movie", "Watch a movie and take a photo.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.PEA, 10), 1));

        questTemplateRepository.save(
            new QuestTemplate(27, "Talk about recent emotions", "Talk about recent emotions.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(28, "Do a simple exercise", "Do a simple exercise and take a photo.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.STR, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(29, "Think of three things you want to hear",
	"Think of three things you want to hear.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(30, "Explain a topic you know well", "Explain a topic you know well.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.KNO, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(31, "List three aspects of yourself you like",
	"List three aspects of yourself you like.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(32, "Tidy up your desk", "Tidy up your desk and take a photo.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.KNO, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(33, "Walk more than 1000 steps",
	"Walk more than 1000 steps and take a photo.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.STR, 7), 1));

        questTemplateRepository.save(
            new QuestTemplate(34, "List 10 of your strengths", "List 10 of your strengths.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(35, "Take a snapshot of your life", "Take a snapshot of your life.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.PEA, 9), 1));

        questTemplateRepository.save(
            new QuestTemplate(36, "Cook and eat a meal", "Cook and eat a meal and take a photo.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.KNO, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(37, "Gratitude journaling", "Write in a gratitude journal.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 7), 1));

        questTemplateRepository.save(
            new QuestTemplate(38, "Visit a nearby cafe", "Visit a nearby cafe and take a photo.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.PEA, 7), 1));

        questTemplateRepository.save(
            new QuestTemplate(39, "What is your dream?", "What is your dream?",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(40, "Do 15 squats", "Do 15 squats and take a photo.",
	ExperiencePoint.from(30), QuestionType.PHOTO,
	StatInfo.of(StatType.STR, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(41, "Write a letter to your future self",
	"Write a letter to your future self.",
	ExperiencePoint.from(30), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 7), 1));

        questTemplateRepository.save(
            new QuestTemplate(42, "Buy clothes", "Buy clothes and take a photo.",
	ExperiencePoint.from(30), QuestionType.PHOTO,
	StatInfo.of(StatType.PEA, 7), 1));

        questTemplateRepository.save(
            new QuestTemplate(43, "Express current emotions in poetry",
	"Express current emotions in poetry.",
	ExperiencePoint.from(30), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(44, "List three things you love", "List three things you love.",
	ExperiencePoint.from(30), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(45, "Write the table of contents for your autobiography",
	"Write the table of contents for your autobiography.",
	ExperiencePoint.from(30), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.KNO, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(46, "Know your limits", "Know your limits.",
	ExperiencePoint.from(30), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(47, "Do a 1-minute plank", "Do a 1-minute plank with a timer.",
	ExperiencePoint.from(30), QuestionType.TIMER,
	StatInfo.of(StatType.STR, 7), 1));

        questTemplateRepository.save(
            new QuestTemplate(48, "List three or more things that immerse you",
	"List three or more things that immerse you.",
	ExperiencePoint.from(30), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 3), 1));

        questTemplateRepository.save(
            new QuestTemplate(49, "Visit a place you've always wanted to go",
	"Visit a place you've always wanted to go and take a photo.",
	ExperiencePoint.from(30), QuestionType.PHOTO,
	StatInfo.of(StatType.PEA, 9), 1));

        questTemplateRepository.save(
            new QuestTemplate(50, "List five things you think you've done well in your life",
	"List five things you think you've done well in your life.",
	ExperiencePoint.from(30), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(51, "Things you dislike", "List things you dislike.",
	ExperiencePoint.from(40), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(52, "Give a compliment to someone around you",
	"Give a compliment to someone around you and take a photo.",
	ExperiencePoint.from(40), QuestionType.PHOTO,
	StatInfo.of(StatType.PEA, 7), 1));

        questTemplateRepository.save(
            new QuestTemplate(53, "Jog in the park", "Jog in the park and take a photo.",
	ExperiencePoint.from(40), QuestionType.PHOTO,
	StatInfo.of(StatType.STR, 9), 1));

        questTemplateRepository.save(
            new QuestTemplate(54, "Talk about what you are currently working on",
	"Talk about what you are currently working on.",
	ExperiencePoint.from(40), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(55, "Sing your favorite song", "Sing your favorite song.",
	ExperiencePoint.from(40), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.PEA, 9), 1));

        questTemplateRepository.save(
            new QuestTemplate(56, "Plank challenge record!",
	"Record your plank challenge with a timer.",
	ExperiencePoint.from(40), QuestionType.TIMER,
	StatInfo.of(StatType.STR, 7), 1));

        questTemplateRepository.save(
            new QuestTemplate(57, "Express current emotions in poetry",
	"Express current emotions in poetry.",
	ExperiencePoint.from(40), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(58, "Reflect on your inner thoughts",
	"Reflect on your inner thoughts.",
	ExperiencePoint.from(40), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(59, "Look for support programs for introverts and loners",
	"Look for support programs for introverts and loners and take a photo.",
	ExperiencePoint.from(40), QuestionType.PHOTO,
	StatInfo.of(StatType.KNO, 9), 1));

        questTemplateRepository.save(
            new QuestTemplate(60, "Speak aloud three things you want to hear",
	"Speak aloud three things you want to hear.",
	ExperiencePoint.from(50), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 7), 1));

        questTemplateRepository.save(
            new QuestTemplate(61, "Contact the most important person to you",
	"Contact the most important person to you and take a photo.",
	ExperiencePoint.from(50), QuestionType.PHOTO,
	StatInfo.of(StatType.PEA, 9), 1));

        questTemplateRepository.save(
            new QuestTemplate(62, "Take a career aptitude test",
	"Take a career aptitude test and take a photo.",
	ExperiencePoint.from(50), QuestionType.PHOTO,
	StatInfo.of(StatType.KNO, 9), 1));

        questTemplateRepository.save(
            new QuestTemplate(63, "List five or more things that make you happy for no reason",
	"List five or more things that make you happy for no reason.",
	ExperiencePoint.from(50), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(64, "Have a conversation with your family",
	"Have a conversation with your family.",
	ExperiencePoint.from(50), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.PEA, 9), 1));

        questTemplateRepository.save(
            new QuestTemplate(65, "Develop your strengths", "Develop your strengths.",
	ExperiencePoint.from(50), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(66, "Research job information for your dream job",
	"Research job information for your dream job.",
	ExperiencePoint.from(50), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.KNO, 9), 1));

        questTemplateRepository.save(
            new QuestTemplate(67, "Describe three aspects of yourself you want to become",
	"Describe three aspects of yourself you want to become.",
	ExperiencePoint.from(50), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 5), 1));

        questTemplateRepository.save(
            new QuestTemplate(68, "Visit the library", "Visit the library and take a photo.",
	ExperiencePoint.from(50), QuestionType.PHOTO,
	StatInfo.of(StatType.PEA, 9), 1));

        questTemplateRepository.save(
            new QuestTemplate(69, "Participate in support programs for introverts and loners",
	"Participate in support programs for introverts and loners.",
	ExperiencePoint.from(50), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.KNO, 9), 1));

        questTemplateRepository.save(
            new QuestTemplate(70, "Respond to your past self", "Respond to your past self.",
	ExperiencePoint.from(50), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 7), 1));
    }
}

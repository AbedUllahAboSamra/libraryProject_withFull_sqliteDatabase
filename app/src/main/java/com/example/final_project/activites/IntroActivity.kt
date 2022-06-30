package com.example.final_project.activites

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import androidx.core.view.isVisible
import com.example.final_project.R
import com.example.final_project.adapters.IntroPageScreenAdapter
import com.example.final_project.databinding.ActivityIntroBinding
import com.example.final_project.dp.SqlDataBase
import com.example.final_project.models.ScreenIntroItem
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //لجعل الشاشه فل سكرين
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        if (restorPrefData()) {

            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()

        }


        var binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

//اضافة الصور اليى ملف التصميم عن طريق الادابتر
        var data = ArrayList<ScreenIntroItem>()
        data.add(
            ScreenIntroItem(
                R.drawable.fintro, "Welcome to the smart\n" +
                        " library application"
            )
        )
        data.add(
            ScreenIntroItem(
                R.drawable.sintro, "The application offers\n" +
                        " a lot of useful books"
            )
        )
        data.add(ScreenIntroItem(R.drawable.tintro, ""))

        var adapter = IntroPageScreenAdapter(this, data)


        binding.viewPagerIntro.adapter = adapter


        //ربط التاب ليوت ب الفيو بيجر
        binding.tabLayout.setupWithViewPager(binding.viewPagerIntro)


//الضغط على الزر و انهاء السكرينه
        var position = 0
//لتحميل الانيميشن
        val anim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.buttominimation)

        binding.btnNext.setOnClickListener {

            position = binding.viewPagerIntro.currentItem
            if (position < data.size) {

                position++
                binding.viewPagerIntro.currentItem = position

            }



            if (position == data.size - 1) {
                binding.tabLayout.isVisible = false
                binding.btnNext.isVisible = false
                binding.btnGetStarted.isVisible = true
                binding.btnGetStarted.animation = anim
            }


        }




        binding.btnGetStarted.setOnClickListener {
            var db = SqlDataBase(this)
            db.addBook(
                "War fan",
                "Sun Tzu",
                "1952-3-9",
                "Wars",
                "Kropp was in Paul's class at school and is described as the clearest thinker of the group as well as the smallest",
                "English",
                5,
                "true",
                R.drawable.addwar1,
                "From November 10 to December 9, 1928, All Quiet on the Western Front was published in serial form in Vossische Zeitung magazine. It was released in book form the following year to smashing success, selling one and a half million copies that same year. It was the best-selling work of fiction in America for the year 1929, according to Publishers Weekly.[6] Although publishers had worried that interest in World War I had waned more than 10 years after the armistice, Remarque's realistic depiction of trench warfare from the perspective of young soldiers struck a chord with the war's survivors—soldiers and civilians alike—and provoked strong reactions, both positive and negative, around the world.\n" +
                        "\n" +
                        "With All Quiet on the Western Front, Remarque emerged as an eloquent spokesman for a generation that had been, in his own words, \"destroyed by war, even though it might have escaped its shells.\" Remarque's harshest critics, in turn, were his countrymen, many of whom felt the book denigrated the German war effort, and that Remarque had exaggerated the horrors of war to further his pacifist agenda. The strongest voices against Remarque came from the emerging Nazi Party and its ideological allies. In 1933, when the Nazis rose to power, All Quiet on the Western Front became one of the first degenerate books to be publicly burnt;[7] in 1930, screenings of the Academy Award-winning film based on the book were met with Nazi-organized protests and mob attacks on both movie theatres and audience members.[8]\n" +
                        "\n" +
                        "Objections to Remarque's portrayal of the World War I German soldiers were not limited to those of the Nazis in 1933. Dr. Karl Kroner [de] was concerned about Remarque's depiction of the medical personnel as being inattentive, uncaring, or absent from frontline action. Dr. Kroner was specifically worried that the book would perpetuate German stereotypes abroad that had subsided since the First World War. He offered the following clarification: “People abroad will draw the following conclusions: if German doctors deal with their own fellow countrymen in this manner, what acts of inhumanity will they not perpetuate against helpless prisoners delivered up into their hands or against the populations of occupied territory?”[9][10]\n" +
                        "\n" +
                        "A fellow patient of Remarque's in the military hospital in Duisburg objected to the negative depictions of the nuns and patients, and of the general portrayal of soldiers: “There were soldiers to whom the protection of homeland, protection of house and homestead, protection of family were the highest objective, and to whom this will to protect their homeland gave the strength to endure any extremities.”[10]\n" +
                        "\n" +
                        "These criticisms suggest that perhaps experiences of the war and the personal reactions of individual soldiers to their experiences may be more diverse than Remarque portrays them; however, it is beyond question that Remarque gives voice to a side of the war and its experience that was overlooked or suppressed at the time. This perspective is crucial to understanding the true effects of World War I. The evidence can be seen in the lingering depression that Remarque and many of his friends and acquaintances were suffering a decade later.[9]",
                2F,
                "",
                "f"

            )
            db.addBook(
                "فنون الحرب",
                "هون شنق",
                "1880-5-3",
                "Wars",
                "فن الحرب (بالصينية: 孫子兵法) هو أطروحة عسكرية صينية كتبت أثناء القرن السادس قبل الميلاد من قبل سون تزو Sun Tzu",
                "arabic",
                20,
                "true",
                R.drawable.addwar2,
                "تروي لنا المخطوطات الصينية أن سون تزو ووه كان مواطناً وجندياً في مملكة تشي؛ وبسبب ذيوع خبرته في فنون الحروب والقتال طلب منه الملك هوو لوو أن يضع خلاصة خبرته وتجاربه في كتاب، فكان الكتاب \"فن الحرب\" ذو الثلاثة عشر فصلاً، فلما انتهى سون تزو من كتابته سأله الملك هوو لوو قائلاً: \"لقد قرأت كتابك: فن الحرب، فهل يمكنني وضع نظرياتك عن إدارة الجنود تحت اختبار بسيط؟\" بالإيجاب جاء جواب سون تزو، فسأله الملك مرة أخرى: \"وهل يمكننا إجراء الاختبار على النساء ؟\" فلم تتغير إجابة سون تزو!\n" +
                        "\n" +
                        "على الفور، تم تجهيز 180 امرأة من جواري قصر الملك؛ قسّمهن سون تزو إلى مجموعتين، وعيّن على رأس كلتا المجموعتين إحدى المحظيات من الجواري، ثم أمرهن بأن يتسلحن بالحراب في أيديهن، ثم خطبهن قائلاً: \"أعتقد أنكن تعلمن الفرق بين المقدمة والمؤخرة، اليد اليمنى واليد اليسرى ؟\" أجابته النسوة: \" نعم!\".\n" +
                        "\n" +
                        "فمضى سون تزو قائلاً: \"عندما أقول: انظرن أمامكن، فيجب عليكن النظر للأمام؛ وعندما أقول \"دُرّن لليسار\" فيجب عليكن الدوران باتجاه أيديكن اليسرى، وعندما أقول \"دُرّن لليمين\" فيجب عليكن الدوران باتجاه أيديكن اليمنى، وعندما أقول \"درّن للخلف\" فيجب عليكن الدوران باتجاه أيديكن اليمنى إلى ورائكن\". فأجابته النسوة بأنهن قد فهمن كلمات الأوامر التي قد شرحها لهن.\n" +
                        "\n" +
                        "قام سون تزو بإعداد الترتيبات من أجل بدء التدريب العسكري؛ ثم على دقات الطبول أعطى أوامره \"درّن لليمين\" لكن النساء انفجرن في الضحك ولم ينفذن الأمر، فعقّب سون تزو قائلاً: \"إذا كانت الكلمات المستخدمة في إصدار الأوامر غير واضحة ومميزة، وإذا كانت تلك الأوامر غير مفهومة فهماً شاملاً، فيقع اللوم وقتها على القائد\".\n" +
                        "\n" +
                        "ثم أكمل تدريبهن فأعطى أوامره \"درّن لليسار\" لكن النساء انفجرن في موجات من الضحك ولم ينفذن الأمر، فعقّب سون تزو قائلاً: \"إذا كانت الأوامر واضحة ومميزة، وإذا كانت الأوامر مفهومة فهماً شاملاً ولم ينفذ الجنود الأوامر، فيقع اللوم وقتها على الجنود\".\n" +
                        "\n" +
                        "هنا أصدر سون تزو الأوامر بقطع رقبة قائدتي كلتا المجموعتين أمام النسوة!\n" +
                        "\n" +
                        "كان الملك يراقب التدريبات من مكان قريب، فلم يسرّه فصل رقبة جاريتيه المحظيتين عنده، فأرسل إلى سون تزو قائلاً: \"لقد أصبحت واثقاً من قدرتك على التعامل مع الجنود، ولن يلذ لي طعام أو شراب بدون هاتين الجاريتين، لذا فإني أرغب في توفير حياتهما!\"\n" +
                        "\n" +
                        "أرسل سون تزو مجيباً رسالة الملك: \"بتكليفكم لي قيادة قواتكم العسكرية، فإن هناك بعض أوامركم التي لا يمكنني قبولها وأنا تحت هذا التكليف\".\n" +
                        "\n" +
                        "وتم إعدام القائدتين أمام النسوة.\n" +
                        "\n" +
                        "وعلى الفور تم تعيين من تليهن في الحظوة لدى الملك كقائدتين للمجموعتين، وتم استئناف التدريب على صوت الطبول فلم يضحك أحد، وتقدمت النسوة في التدريبات العسكرية بكل دقة وانضباط دون أن يخاطرن بإصدار أي صوت!\n" +
                        "\n" +
                        "ثم أرسل سون تزو رسالة إلى الملك قائلاً: \"لقد تم تدريب وتنظيم جنودكن، وهن الآن على أتم الاستعداد لكي تستعرضهن، يمكنكم الآن استخدامهن في أي رغبة يشاؤها مليكهن، اصدر لهم الأمر فيخوضوا خلال الماء والنار\".\n" +
                        "\n" +
                        "إلا إن الملك رد عليه: \" فليعُد قائد الجيوش وليُنهى التدريب، بالنسبة لنا فلا رغبة عندي في استعراض الجنود\".\n" +
                        "\n" +
                        "عندئذ قال سون تزو: \"إن الملك مُغرم فقط بالكلمات، لا يستطيع ترجمتها إلى أفعال\".\n" +
                        "\n" +
                        "استقر في ذهن الملك هوو لوو تمكن سون تزو من إدارة الجنود، فعينه القائد العام للجيوش، وأرسله ليحارب مملكة تشوو المجاورة فهزمها وشق طريقه إلى عاصمتها ينج، ثم إلى الشمال حيث زرع الخوف في مملكتي تشي وتشن؛ ومن نصر إلى آخر فذاع صيت سون تزو وتوسعت مملكة هوو لوو. تروى لنا المخطوطات الصينية كيف انتصر سون تزو بجيش قوامه 30 ألف جندي على جيش عدوه الذي قوامه 200 ألف جندي، بسبب افتقار عدوه إلى عنصري التنظيم والإدارة.\n" +
                        "\n" +
                        "من أشهر مقولات سون: إن كنت تعلم قدراتك وقدرات خصمك، فما عليك أن تخشى من نتائج مئة معركة. وإن كنت تعرف قدرات نفسك، وتجهل قدرات خصمك، فلسوف تعاني من هزيمة ما بعد كل نصر مُكتسب. أما إن كنت تجهل قدرات نفسك، وتجهل قدرات خصمك.. فالهزيمة المؤكدة هي حليفك في كل معركة.",
                2F,
                "",
                "f"

            )
            //cook
            db.addBook(
                "Gluten-Cooking",
                "Jamie Oliver",
                "2015-1-22",
                "Cook",
                "When I was asked to write Gluten-Free Cooking For Dummies",
                "English",
                30,
                "false",
                R.drawable.addcook1,
                "If you actually have gluten sensitivity and not celiac disease, you\n" +
                        "may be able to get away with eating gluten from time to time. Just\n" +
                        "make sure you remember those pesky false negatives and\n" +
                        "misdiagnoses, and make sure you don’t have celiac disease if you’re\n" +
                        "going to indulge.\n" +
                        "Some people are told they’re gluten sensitive when they really do\n" +
                        "have celiac disease. If that scenario applies to you and you continue\n" +
                        "to eat gluten, even if it’s just every once in a while, you could do\n" +
                        "some serious unseen damage..",
                5F,
                "",
                "f"
            )
            db.addBook(
                "recipes Cook Book",
                "Paul Bocuse",
                "2019-12-12",
                "Cook",
                "When I was asked to write Gluten-Free Cooking For Dummies",
                "English",
                10,
                "false",
                R.drawable.addcook2,
                "Several credible double-blind, placebo-controlled studies are\n" +
                        "underway at reputable universities to study the relationship\n" +
                        "between gluten and autism. The results of these studies are eagerly\n" +
                        "anticipated and will most likely have a dramatic affect on the way\n" +
                        "pediatricians view the disorder.\n" +
                        "For now, I summarize what is known. Gastrointestinal problems\n" +
                        "seem to be more prevalent in people with autism than in the general\n" +
                        "public — do they have a higher incidence of celiac disease?",
                3.5F,
                "",
                "f"
            )
            db.addBook(
                "recipes Cook Book",
                "George Orwell",
                "1984-2-9",
                "Fiction",
                "To see what your friends thought of this book",
                "English",
                5,
                "true",
                R.drawable.addfiction1,
                "Among the seminal texts of the 20th century, Nineteen Eighty-Four is a rare work that grows more haunting as its futuristic purgatory becomes more real. Published in 1949, the book offers political satirist George Orwell's nightmarish vision of a totalitarian, bureaucratic world and one poor stiff's attempt to find individuality. The brilliance of the novel is Orwell's pre   3.5F,",
                4F,
                "",
                "f"
            )
            db.addBook(
                "recipes Cook Book",
                "George Orwell",
                "2006-9-3",
                "Secrets",
                "The Secret is a 2006 self-help book by Rhonda Byrne, based on the earlier film of the same name.",
                "English",
                5,
                "true",
                R.drawable.addsceets,
                "US TV host Oprah Winfrey is a proponent of the book. On The Larry King Show she said that the message of The Secret is the message she's been trying to share with the world on her show for the past 21 years.[12] Author Rhonda Byrne was later invited to her show along with people who swear by The Secret.[13]\n" +
                        "\n" +
                        "Valerie Frankel of Good Housekeeping wrote an article about her trying the principles of The Secret for four weeks. While she reached some of her goals, others had improved. Frankel's final assessment is: \"Counting my blessings has been uplifting, reminding me of what's already great about my life. Visualization has forced me to pay attention to what I really desire. And laughing is never a bad idea. If you ignore The Secret's far-too-simplistic maxims (no, you will not be doomed to a miserable life for thinking negative thoughts) and the hocus-pocus (the cosmos isn't going to deliver a new car; it's busy), there's actually some helpful advice in the book. But it's nothing you don't already know.",
                                    4F,
                "",
                "f"
            )
            //

            db.addBook(
                "Gluten-romansy",
                "Jamie Oliver",
                "2015-1-22",
                "Romantic",
                "When I was asked to write Gluten-Free Cooking For Dummies",
                "English",
                30,
                "false",
                R.drawable.romansy,
                "If you actually have gluten sensitivity and not celiac disease, you\n" +
                        "may be able to get away with eating gluten from time to time. Just\n" +
                        "make sure you remember those pesky false negatives and\n" +
                        "misdiagnoses, and make sure you don’t have celiac disease if you’re\n" +
                        "going to indulge.\n" +
                        "Some people are told they’re gluten sensitive when they really do\n" +
                        "have celiac disease. If that scenario applies to you and you continue\n" +
                        "to eat gluten, even if it’s just every once in a while, you could do\n" +
                        "some serious unseen damage..",
                5F,
                "",
                "f"
            )
            db.addBook(
                "War fan",
                "Sun Tzu",
                "1952-3-9",
                "World War",
                "Kropp was in Paul's class at school and is described as the clearest thinker of the group as well as the smallest",
                "English",
                5,
                "true",
                R.drawable.addwar1,
                "From November 10 to December 9, 1928, All Quiet on the Western Front was published in serial form in Vossische Zeitung magazine. It was released in book form the following year to smashing success, selling one and a half million copies that same year. It was the best-selling work of fiction in America for the year 1929, according to Publishers Weekly.[6] Although publishers had worried that interest in World War I had waned more than 10 years after the armistice, Remarque's realistic depiction of trench warfare from the perspective of young soldiers struck a chord with the war's survivors—soldiers and civilians alike—and provoked strong reactions, both positive and negative, around the world.\n" +
                        "\n" +
                        "With All Quiet on the Western Front, Remarque emerged as an eloquent spokesman for a generation that had been, in his own words, \"destroyed by war, even though it might have escaped its shells.\" Remarque's harshest critics, in turn, were his countrymen, many of whom felt the book denigrated the German war effort, and that Remarque had exaggerated the horrors of war to further his pacifist agenda. The strongest voices against Remarque came from the emerging Nazi Party and its ideological allies. In 1933, when the Nazis rose to power, All Quiet on the Western Front became one of the first degenerate books to be publicly burnt;[7] in 1930, screenings of the Academy Award-winning film based on the book were met with Nazi-organized protests and mob attacks on both movie theatres and audience members.[8]\n" +
                        "\n" +
                        "Objections to Remarque's portrayal of the World War I German soldiers were not limited to those of the Nazis in 1933. Dr. Karl Kroner [de] was concerned about Remarque's depiction of the medical personnel as being inattentive, uncaring, or absent from frontline action. Dr. Kroner was specifically worried that the book would perpetuate German stereotypes abroad that had subsided since the First World War. He offered the following clarification: “People abroad will draw the following conclusions: if German doctors deal with their own fellow countrymen in this manner, what acts of inhumanity will they not perpetuate against helpless prisoners delivered up into their hands or against the populations of occupied territory?”[9][10]\n" +
                        "\n" +
                        "A fellow patient of Remarque's in the military hospital in Duisburg objected to the negative depictions of the nuns and patients, and of the general portrayal of soldiers: “There were soldiers to whom the protection of homeland, protection of house and homestead, protection of family were the highest objective, and to whom this will to protect their homeland gave the strength to endure any extremities.”[10]\n" +
                        "\n" +
                        "These criticisms suggest that perhaps experiences of the war and the personal reactions of individual soldiers to their experiences may be more diverse than Remarque portrays them; however, it is beyond question that Remarque gives voice to a side of the war and its experience that was overlooked or suppressed at the time. This perspective is crucial to understanding the true effects of World War I. The evidence can be seen in the lingering depression that Remarque and many of his friends and acquaintances were suffering a decade later.[9]",
                2F,
                "",
                "f"

            )
            db.addBook(
                "فنون الحرب",
                "هون شنق",
                "1880-5-3",
                "World War",
                "فن الحرب (بالصينية: 孫子兵法) هو أطروحة عسكرية صينية كتبت أثناء القرن السادس قبل الميلاد من قبل سون تزو Sun Tzu",
                "arabic",
                20,
                "true",
                R.drawable.addwar2,
                "تروي لنا المخطوطات الصينية أن سون تزو ووه كان مواطناً وجندياً في مملكة تشي؛ وبسبب ذيوع خبرته في فنون الحروب والقتال طلب منه الملك هوو لوو أن يضع خلاصة خبرته وتجاربه في كتاب، فكان الكتاب \"فن الحرب\" ذو الثلاثة عشر فصلاً، فلما انتهى سون تزو من كتابته سأله الملك هوو لوو قائلاً: \"لقد قرأت كتابك: فن الحرب، فهل يمكنني وضع نظرياتك عن إدارة الجنود تحت اختبار بسيط؟\" بالإيجاب جاء جواب سون تزو، فسأله الملك مرة أخرى: \"وهل يمكننا إجراء الاختبار على النساء ؟\" فلم تتغير إجابة سون تزو!\n" +
                        "\n" +
                        "على الفور، تم تجهيز 180 امرأة من جواري قصر الملك؛ قسّمهن سون تزو إلى مجموعتين، وعيّن على رأس كلتا المجموعتين إحدى المحظيات من الجواري، ثم أمرهن بأن يتسلحن بالحراب في أيديهن، ثم خطبهن قائلاً: \"أعتقد أنكن تعلمن الفرق بين المقدمة والمؤخرة، اليد اليمنى واليد اليسرى ؟\" أجابته النسوة: \" نعم!\".\n" +
                        "\n" +
                        "فمضى سون تزو قائلاً: \"عندما أقول: انظرن أمامكن، فيجب عليكن النظر للأمام؛ وعندما أقول \"دُرّن لليسار\" فيجب عليكن الدوران باتجاه أيديكن اليسرى، وعندما أقول \"دُرّن لليمين\" فيجب عليكن الدوران باتجاه أيديكن اليمنى، وعندما أقول \"درّن للخلف\" فيجب عليكن الدوران باتجاه أيديكن اليمنى إلى ورائكن\". فأجابته النسوة بأنهن قد فهمن كلمات الأوامر التي قد شرحها لهن.\n" +
                        "\n" +
                        "قام سون تزو بإعداد الترتيبات من أجل بدء التدريب العسكري؛ ثم على دقات الطبول أعطى أوامره \"درّن لليمين\" لكن النساء انفجرن في الضحك ولم ينفذن الأمر، فعقّب سون تزو قائلاً: \"إذا كانت الكلمات المستخدمة في إصدار الأوامر غير واضحة ومميزة، وإذا كانت تلك الأوامر غير مفهومة فهماً شاملاً، فيقع اللوم وقتها على القائد\".\n" +
                        "\n" +
                        "ثم أكمل تدريبهن فأعطى أوامره \"درّن لليسار\" لكن النساء انفجرن في موجات من الضحك ولم ينفذن الأمر، فعقّب سون تزو قائلاً: \"إذا كانت الأوامر واضحة ومميزة، وإذا كانت الأوامر مفهومة فهماً شاملاً ولم ينفذ الجنود الأوامر، فيقع اللوم وقتها على الجنود\".\n" +
                        "\n" +
                        "هنا أصدر سون تزو الأوامر بقطع رقبة قائدتي كلتا المجموعتين أمام النسوة!\n" +
                        "\n" +
                        "كان الملك يراقب التدريبات من مكان قريب، فلم يسرّه فصل رقبة جاريتيه المحظيتين عنده، فأرسل إلى سون تزو قائلاً: \"لقد أصبحت واثقاً من قدرتك على التعامل مع الجنود، ولن يلذ لي طعام أو شراب بدون هاتين الجاريتين، لذا فإني أرغب في توفير حياتهما!\"\n" +
                        "\n" +
                        "أرسل سون تزو مجيباً رسالة الملك: \"بتكليفكم لي قيادة قواتكم العسكرية، فإن هناك بعض أوامركم التي لا يمكنني قبولها وأنا تحت هذا التكليف\".\n" +
                        "\n" +
                        "وتم إعدام القائدتين أمام النسوة.\n" +
                        "\n" +
                        "وعلى الفور تم تعيين من تليهن في الحظوة لدى الملك كقائدتين للمجموعتين، وتم استئناف التدريب على صوت الطبول فلم يضحك أحد، وتقدمت النسوة في التدريبات العسكرية بكل دقة وانضباط دون أن يخاطرن بإصدار أي صوت!\n" +
                        "\n" +
                        "ثم أرسل سون تزو رسالة إلى الملك قائلاً: \"لقد تم تدريب وتنظيم جنودكن، وهن الآن على أتم الاستعداد لكي تستعرضهن، يمكنكم الآن استخدامهن في أي رغبة يشاؤها مليكهن، اصدر لهم الأمر فيخوضوا خلال الماء والنار\".\n" +
                        "\n" +
                        "إلا إن الملك رد عليه: \" فليعُد قائد الجيوش وليُنهى التدريب، بالنسبة لنا فلا رغبة عندي في استعراض الجنود\".\n" +
                        "\n" +
                        "عندئذ قال سون تزو: \"إن الملك مُغرم فقط بالكلمات، لا يستطيع ترجمتها إلى أفعال\".\n" +
                        "\n" +
                        "استقر في ذهن الملك هوو لوو تمكن سون تزو من إدارة الجنود، فعينه القائد العام للجيوش، وأرسله ليحارب مملكة تشوو المجاورة فهزمها وشق طريقه إلى عاصمتها ينج، ثم إلى الشمال حيث زرع الخوف في مملكتي تشي وتشن؛ ومن نصر إلى آخر فذاع صيت سون تزو وتوسعت مملكة هوو لوو. تروى لنا المخطوطات الصينية كيف انتصر سون تزو بجيش قوامه 30 ألف جندي على جيش عدوه الذي قوامه 200 ألف جندي، بسبب افتقار عدوه إلى عنصري التنظيم والإدارة.\n" +
                        "\n" +
                        "من أشهر مقولات سون: إن كنت تعلم قدراتك وقدرات خصمك، فما عليك أن تخشى من نتائج مئة معركة. وإن كنت تعرف قدرات نفسك، وتجهل قدرات خصمك، فلسوف تعاني من هزيمة ما بعد كل نصر مُكتسب. أما إن كنت تجهل قدرات نفسك، وتجهل قدرات خصمك.. فالهزيمة المؤكدة هي حليفك في كل معركة.",
                2F,
                "",
                "f"

            )
            db.addBook(
                "Fated Blades",
                " Ilona Andrews",
                "1952-3-9",
                "Romantic",
                "An uneasy alliance between warring families gets heated in this otherworldly novella from bestselling author Ilona Andrews.",
                "English",
                5,
                "false",
                R.drawable.addromansitic1,
                "An uneasy alliance between warring families gets heated in this otherworldly novella from bestselling author Ilona Andrews.\n" +
                        "\n" +
                        "At first glance, the planet Rada seems like a lush paradise. But the ruling families, all boasting genetically enhanced abilities, are in constant competition for power―and none more so than the Adlers and the Baenas. For generations, the powerful families have pushed and pulled each other in a dance for dominance.\n" +
                        "\n" +
                        "Until a catastrophic betrayal from within changes everything.\n" +
                        "\n" +
                        "Now, deadly, disciplined, and solitary leaders Ramona Adler and Matias Baena must put aside their enmity and work together in secret to prevent sinister forces from exploiting universe-altering technology. Expecting to suffer through their uneasy alliance, Ramona and Matias instead discover that they understand each other as no one in their families can―and that their combined skills may eclipse the risks of their forbidden alliance.\n" +
                        "\n" +
                        "As the two warriors risk their lives to save their families, they must decide whether to resist or embrace the passion simmering between them. For now, the dance between their families continues―but just one misstep could spell the end of them both. (less)",
                4.5F,
                "",
                "f"

            )
            db.addBook(
                "The Nobleman's",
                " Mackenzi Lee",
                "1952-3-9",
                "World War",
                "Adrian Montague has a bright future. The sole heir to his father's estate, he is an up and coming political writer and engaged to an activist who challenges and inspires him",
                "English",
                5,
                "true",
                R.drawable.addeomansitic2,
                "he is an up and coming political writer and engaged to an activist who challenges and inspires him. But most young Lords aren't battling the debilitating anxiety Adrian secretly lives with, or the growing fear that it might consume him and all he hopes to accomplish. In the wake of his mother's unexpected death, Adrian is also concerned people will find out that he has the mental illness she struggled with for years.\n" +
                        "\n" +
                        "When a newly found keepsake of hers-a piece of a broken spyglass-comes into Adrian's possession, he's thrust into the past and finds himself face to face with an older brother he never knew he had. Henry \"Monty\" Montague has been living quietly in London for years, and his sudden appearance sends Adrian on a quest to unravel family secrets that only the spyglass can answer.\n" +
                        "\n" +
                        "In pursuit of answers about the relic, the brothers chart a course to locate their sister Felicity. But as they travel between the pirate courts of Rabat, Portuguese islands, the canals of Amsterdam, and into unknown Artic waters, the Montague siblings are thrown into one final adventure as they face a ghostly legend that threatens their whole family.\n" +
                        "\n" +
                        "Return to the enchanting world of the Montague siblings in the finale to the New York Times bestselling and Stonewall Honor-winning series, featuring a teenage Adrian Montague as he desperately seeks the now adult Monty and Felicity-the older siblings he never knew he had" +
                        "\n" +
                        "A fellow patient of Remarque's in the military hospital in Duisburg objected to the negative depictions of the nuns and patients, and of the general portrayal of soldiers: “There were soldiers to whom the protection of homeland, protection of house and homestead, protection of family were the highest objective, and to whom this will to protect their homeland gave the strength to endure any extremities.”[10]\n" +
                        "\n" +
                        "These criticisms suggest that perhaps experiences of the war and the personal reactions of individual soldiers to their experiences may be more diverse than Remarque portrays them; however, it is beyond question that Remarque gives voice to a side of the war and its experience that was overlooked or suppressed at the time. This perspective is crucial to understanding the true effects of World War I. The evidence can be seen in the lingering depression that Remarque and many of his friends and acquaintances were suffering a decade later.[9]",
                1.5F,
                "",
                "f"

            )


            var i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            savePrefIntro()
            finish()


        }

        binding.tabLayout.addOnTabSelectedListener(

            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab!!.position == data.size - 1) {
                        binding.tabLayout.isVisible = false
                        binding.btnNext.isVisible = false
                        binding.btnGetStarted.isVisible = true

                        binding.btnGetStarted.animation = anim
                    }


                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }


            }


        )


    }

    private fun restorPrefData(): Boolean {

        var shared = getSharedPreferences("app", MODE_PRIVATE)
        var istrue = shared.getBoolean("is_intro", false)
        return istrue

    }

    fun savePrefIntro() {
        var shared = getSharedPreferences("app", MODE_PRIVATE)
        var editor = shared.edit()

        editor.putBoolean("is_intro", true)
        editor.commit()

    }

}
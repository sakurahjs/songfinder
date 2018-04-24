Korean Karaoke Song Finder
==========================

Korean Karaoke Song Finder is a library that finds song information from Major Karaoke web sites.
There are two major Karaoke companies in Korea, Keum-young and TJ media.

So, Korean Karaoke Song Finder parses `Keum-young`_ and `TJ media`_ web sites just like you search songs in the web sites.
The results of this library are JSON type string.

This library depends on `Keum-young`_ and `TJ media`_ web sites' structure.
If those web sites be renewed, this library must be modified.
`Keum-young`_ web site's response time is quite late, so be careful when using KySongFinder.

This library's dependency is `jsoup`_ library because it sends http requests and receives http responses via `jsoup`_.
You have to set up `jsoup`_ library in your project before using this library.

.. _Keum-young: http://www.ikaraoke.kr/isong/search_song.asp
.. _TJ media: http://www.tjmedia.com
.. _jsoup: https://jsoup.org

Install
-------

1) Download jsoup library and add it to your project build path.
2) Download `korean-karaoke-song-finder-0.0.1.jar`_ and add it to your project build path.

.. _korean-karaoke-song-finder-0.0.1.jar:

Use
---
- import ::

   import com.github.sakurahjs.songfinder.tj.*;
   import com.github.sakurahjs.songfinder.ky.*;

- TJ media
 - TjMonthlyNewSongCollector
  - You can get new monthly release songs' lists through this class ::

   TjMonthlyNewSongCollector tjMonthlyNewSongCollector = new TjMonthlyNewSongCollector();
   String json = tjMonthlyNewSongCollector.execute();

  - The result is like ::

   [{"number" : 97673, "title" : "기적같은이야기", "singer" : "에이핑크", "lyricist" : "박초롱,BEOM X NANG 3,LOOGONE,김지혜,BEOM X NANG 1", "songWriter" : "정은지,BEOM X NANG 3,LOOGONE,김지혜,BEOM X NANG 1"}, ...]

 - TjMonthlyPopularSongCollector
  - You can get monthly most shouted songs' lists through this class ::

   TjMonthlyPopularSongCollector tjMonthlyPopularSongCollector = new TjMonthlyPopularSongCollector();
   String json = tjMonthlyPopularSongCollector.execute();

  - The result is like ::

   [{"rank": 1, "number" : 97017, "title" : "그날처럼", "singer" : "장덕철"}, ...]

 - TjSongFinder
  - You can search TJ media songs through this class.
   - default search : search by title with low precision (low precision means that search results are not 100% matched with the search text.) . ::

   TjSongFinder tjSongFinder = new TjSongFinder();
   String json = tjSongFinder.search("박하사탕").execute();

    - The result is like ::

   [{"number" : 62081, "title" : "박하사탕", "singer" : "윤도현밴드", "lyricist" : "윤도현,김진원", "songWriter" : "윤도현"}, ...]

   - title search with high precision (high precision means that search results are 100% matched with the search text.) ::

   TjSongFinder tjSongFinder = new TjSongFinder();
   String json = tjSongFinder.search("박하사탕").byTitle().withHighPrecision().execute();

    - The result is like ::

   [{"number" : 62081, "title" : "박하사탕", "singer" : "윤도현밴드", "lyricist" : "윤도현,김진원", "songWriter" : "윤도현"}, ...]

   - title search with low precision ::

   TjSongFinder tjSongFinder = new TjSongFinder();
   String json = tjSongFinder.search("사랑했지만").byTitle().withLowPrecision().execute();

    - The result is like ::

   [{"number" : 62081, "title" : "박하사탕", "singer" : "윤도현밴드", "lyricist" : "윤도현,김진원", "songWriter" : "윤도현"}, ...]

   - song number search with high precision ::

   TjSongFinder tjSongFinder = new TjSongFinder();
   String json = tjSongFinder.search("9699").bySongNumber().withHighPrecision().execute();

    - The result is like ::

   [{"number" : 9699, "title" : "박하사탕", "singer" : "윤도현밴드", "lyricist" : "윤도현,김진원", "songWriter" : "윤도현"}]

   - song number search with low precision ::

   TjSongFinder tjSongFinder = new TjSongFinder();
   String json = tjSongFinder.search("9699").bySongNumber().withLowPrecision().execute();

    - The result is like ::

   [{"number" : 96999, "title" : "쏴쏴쏴", "singer" : "이설", "lyricist" : "이설", "songWriter" : "JINO"}, ...]

   - singer search with high precision ::

   TjSongFinder tjSongFinder = new TjSongFinder();
   String json = tjSongFinder.search("윤도현밴드").bySinger().withHighPrecision().execute();

    - The result is like ::

    [{"number" : 62254, "title" : "나는나비", "singer" : "윤도현밴드", "lyricist" : "박태희", "songWriter" : "박태희"}, ...]

   - singer search with low precision ::

   TjSongFinder tjSongFinder = new TjSongFinder();
   String json = tjSongFinder.search("윤도현밴드").bySinger().withLowPrecision().execute();

    - The result is like ::

   [{"number" : 62254, "title" : "나는나비", "singer" : "윤도현밴드", "lyricist" : "박태희", "songWriter" : "박태희"}, ...]

   - lyricist search with high precision ::

   TjSongFinder tjSongFinder = new TjSongFinder();
   String json = tjSongFinder.search("강은경").byLyricist().withHighPrecision().execute();

    - The result is like ::

   [{"number" : 97377, "title" : "세상엔없는사랑", "singer" : "어반자카파", "lyricist" : "강은경", "songWriter" : "이경섭"}, ...]

   - lyricist search with low precision ::

   TjSongFinder tjSongFinder = new TjSongFinder();
   String json = tjSongFinder.search("강은경").byLyricist().withLowPrecision().execute();

    - The result is like ::

    [{"number" : 97377, "title" : "세상엔없는사랑", "singer" : "어반자카파", "lyricist" : "강은경", "songWriter" : "이경섭"}, ...]

   - song writer search with high precision ::

   TjSongFinder tjSongFinder = new TjSongFinder();
   String json = tjSongFinder.search("이경섭").bySongWriter().withHighPrecision().execute();

    - The result is like ::

    [{"number" : 97377, "title" : "세상엔없는사랑", "singer" : "어반자카파", "lyricist" : "강은경", "songWriter" : "이경섭"}, ...]

   - song writer search with low precision ::

   TjSongFinder tjSongFinder = new TjSongFinder();
   String json = tjSongFinder.search("이경섭").bySongWriter().withLowPrecision().execute();

    - The result is like ::

   [{"number" : 97377, "title" : "세상엔없는사랑", "singer" : "어반자카파", "lyricist" : "강은경", "songWriter" : "이경섭"}, ...]

- Keum-young
 - KySongFinder
  - You can search Keum-young songs through this class.
   - default search : search by title with low precision (low precision means that search results are not 100% matched with the search text.) . ::

   KySongFinder kySongFinder = new KySongFinder();
   String json = kySongFinder.search("박하사탕").execute();

    - The result is like ::

   [{"number" : 7640, "title" : "박하사탕", "singer" : "윤도현밴드(YB)", "songWriterAndLyricist" : "윤도현 작곡 김진원,윤도현 작사", "lyrics" : "떠나려 하네 저 강물 따라서 돌아가고파 순수했던 시절 끝나지 않은 더러운 내 삶에 보이는 것은 얼룩진 추억속의 나 고통의 시간만 보낸뒤에는 텅빈 하늘만이 아름다웠네 그 하늘마저 희미해지고 내 갈곳은 다시 못 올 그곳뿐야 오- 열어줘 제발 다시 한번만 두려움에 떨고 있어 열어줘 제발 다시 한번만- 단 한번 만이라도 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로 남아 있는건 아무것도 없어 그 시간들도 다시 오진 않아 어지러워 눈을 감고 싶어 내 갈곳은 다시 못올 그곳뿐야 오- 열어줘 제발 다시 한번만 두려움에 떨고있어 열어줘 제발 다시 한번만- 단 한번만이라도 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로- 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로"}, ...]

   - no lyrics option : drop lyrics from json results ::

   KySongFinder kySongFinder = new KySongFinder();
   String json = kySongFinder.search("박하사탕").noLyrics().execute();

    - The result is like ::

    [{"number" : 7640, "title" : "박하사탕", "singer" : "윤도현밴드(YB)", "songWriterAndLyricist" : "윤도현 작곡 김진원,윤도현 작사"}, ...]

   - title search with low precision

   KySongFinder kySongFinder = new KySongFinder();
   String json = kySongFinder.search("박하사탕").byTitle().execute();

    - The result is like ::

    [{"number" : 7640, "title" : "박하사탕", "singer" : "윤도현밴드(YB)", "songWriterAndLyricist" : "윤도현 작곡 김진원,윤도현 작사", "lyrics" : "떠나려 하네 저 강물 따라서 돌아가고파 순수했던 시절 끝나지 않은 더러운 내 삶에 보이는 것은 얼룩진 추억속의 나 고통의 시간만 보낸뒤에는 텅빈 하늘만이 아름다웠네 그 하늘마저 희미해지고 내 갈곳은 다시 못 올 그곳뿐야 오- 열어줘 제발 다시 한번만 두려움에 떨고 있어 열어줘 제발 다시 한번만- 단 한번 만이라도 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로 남아 있는건 아무것도 없어 그 시간들도 다시 오진 않아 어지러워 눈을 감고 싶어 내 갈곳은 다시 못올 그곳뿐야 오- 열어줘 제발 다시 한번만 두려움에 떨고있어 열어줘 제발 다시 한번만- 단 한번만이라도 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로- 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로"}, ...]

   - title search with high precision (high precision means that search results are 100% matched with the search text.) ::

   KySongFinder kySongFinder = new KySongFinder();
   String json = kySongFinder.search("박하사탕").byTitleWithHighPrecision().execute();

    - The result is like ::

    [{"number" : 7640, "title" : "박하사탕", "singer" : "윤도현밴드(YB)", "songWriterAndLyricist" : "윤도현 작곡 김진원,윤도현 작사", "lyrics" : "떠나려 하네 저 강물 따라서 돌아가고파 순수했던 시절 끝나지 않은 더러운 내 삶에 보이는 것은 얼룩진 추억속의 나 고통의 시간만 보낸뒤에는 텅빈 하늘만이 아름다웠네 그 하늘마저 희미해지고 내 갈곳은 다시 못 올 그곳뿐야 오- 열어줘 제발 다시 한번만 두려움에 떨고 있어 열어줘 제발 다시 한번만- 단 한번 만이라도 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로 남아 있는건 아무것도 없어 그 시간들도 다시 오진 않아 어지러워 눈을 감고 싶어 내 갈곳은 다시 못올 그곳뿐야 오- 열어줘 제발 다시 한번만 두려움에 떨고있어 열어줘 제발 다시 한번만- 단 한번만이라도 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로- 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로"}, ...]

   - song number search ::

   KySongFinder kySongFinder = new KySongFinder();
   String json = kySongFinder.search("7640").bySongNumber().execute();

    - The result is like ::

    [{"number" : 7640, "title" : "박하사탕", "singer" : "윤도현밴드(YB)", "songWriterAndLyricist" : "윤도현 작곡 김진원,윤도현 작사", "lyrics" : "떠나려 하네 저 강물 따라서 돌아가고파 순수했던 시절 끝나지 않은 더러운 내 삶에 보이는 것은 얼룩진 추억속의 나 고통의 시간만 보낸뒤에는 텅빈 하늘만이 아름다웠네 그 하늘마저 희미해지고 내 갈곳은 다시 못 올 그곳뿐야 오- 열어줘 제발 다시 한번만 두려움에 떨고 있어 열어줘 제발 다시 한번만- 단 한번 만이라도 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로 남아 있는건 아무것도 없어 그 시간들도 다시 오진 않아 어지러워 눈을 감고 싶어 내 갈곳은 다시 못올 그곳뿐야 오- 열어줘 제발 다시 한번만 두려움에 떨고있어 열어줘 제발 다시 한번만- 단 한번만이라도 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로- 나 돌아갈래 어릴적 꿈에 나 돌아갈래 그곳으로"}]

   - singer search ::

   KySongFinder kySongFinder = new KySongFinder();
   String json = kySongFinder.search("김흥국").bySinger().execute();

    - The result is like ::

    [{"number" : 1347, "title" : "59년 왕십리", "singer" : "김흥국", "songWriterAndLyricist" : "이혜민 작곡 이혜민 작사", "lyrics" : "왕십리 밤 거리에 구슬프게 비가 내리면 눈물을 삼키려 술을 마신다 옛사랑을- 마신다 정주던 사람은 모두 떠나고 서울 하늘아래 나홀로 아- 깊어가는 가을 밤만이 왕십리를 달래주네 왕십리 밤 거리에 구슬프게 비가 내리면 눈물을 삼키려 술을 마신다 옛 사랑을- 마신다 정주던 사람은 모두 떠나고 서울 하늘아래 나홀로 아- 깊어가는 가을 밤 만이 왕십리를 달래주네 아- 깊어가는 가을 밤만이 왕십리를 달래주네"}, ...]

   - lyricist search ::

   KySongFinder kySongFinder = new KySongFinder();
   String json = kySongFinder.search("강은경").byLyricist().execute();

    - The result is like ::

   [{"number" : 6270, "title" : "1004", "singer" : "조성모", "songWriterAndLyricist" : "이경섭 작곡 강은경 작사", "lyrics" : "하얀 별처럼 환한 그대 미소 이 세상 어느 빛 보다도 나를 눈부시게 해 하얀 눈처럼 닿고 싶은 그댄 내가 알던 그누구보다도 나를 설레게 해 잠시 스친 기억속에 난-- 천사의 연인이었어 다시 그댈 내 눈속에-- 그려 볼 수 있을까 이렇게도 소중한 그댄 내 삶의 가장 기쁜 선물-- 무엇으로 보답해 하얀 새처럼 고운 그대 숨결 이 세상 어느 곳 보다도 내겐 더 따뜻해 잠시 스친 기억속에 난-- 천사의 연인이었어 다시 그댈 내 눈속에-- 그려 볼 수 있을까 이렇게도 소중한 그댄 내 삶의 가장 기쁜 선물-- 무엇으로 보답해 영원히 그댈 간직하고 싶어 그대는 하늘이 보내준 아름다운 선물"}, ...]

   - song writer search ::

   KySongFinder kySongFinder = new KySongFinder();
   String json = kySongFinder.search("이경섭").bySongWriter().execute();

    - The result is like ::

    [{"number" : 6270, "title" : "1004", "singer" : "조성모", "songWriterAndLyricist" : "이경섭 작곡 강은경 작사", "lyrics" : "하얀 별처럼 환한 그대 미소 이 세상 어느 빛 보다도 나를 눈부시게 해 하얀 눈처럼 닿고 싶은 그댄 내가 알던 그누구보다도 나를 설레게 해 잠시 스친 기억속에 난-- 천사의 연인이었어 다시 그댈 내 눈속에-- 그려 볼 수 있을까 이렇게도 소중한 그댄 내 삶의 가장 기쁜 선물-- 무엇으로 보답해 하얀 새처럼 고운 그대 숨결 이 세상 어느 곳 보다도 내겐 더 따뜻해 잠시 스친 기억속에 난-- 천사의 연인이었어 다시 그댈 내 눈속에-- 그려 볼 수 있을까 이렇게도 소중한 그댄 내 삶의 가장 기쁜 선물-- 무엇으로 보답해 영원히 그댈 간직하고 싶어 그대는 하늘이 보내준 아름다운 선물"}, ...]

## MapleM 정보찾기 앱 (with compose)
nexon open api를 이용해 Maple, MapleM 캐릭터 정보를 찾고 저장할 수 있는 앱입니다.  
### 현재 api로 제공되는 정보가 적어 개발이 중단되었습니다.  
##  개발환경
Kotlin = Kotlin 1.8  
Java = Java 17  
gradle : 8.0  
IDE = Android Studio Giraffe (2022.3.1)  

## 주의사항
현재 해당 프로젝트에는 API KEY가 담겨있지않습니다.  
만약 테스트를 해보고싶으시다면 secrets.defaults.properties 값을 변경하시면 됩니다.  
API_KEY = ${nexon_open_api}  
## screenshots  
![메인화면](https://github.com/hegunhee/MapleMFinder/assets/57277631/d4188a4a-220c-46dc-9bfd-c9c432e2ab5b)  
![상세화면](https://github.com/hegunhee/MapleMFinder/assets/57277631/0a2b4f98-a08c-40d5-9908-b57e216c6a43)  
![검색화면](https://github.com/hegunhee/MapleMFinder/assets/57277631/3b3464f6-864a-4837-b1af-09f38e50cb04)  
## 기술스택  
network - retrofit2, moshi, coroutine  
ui - compose, Flows, ViewModel  
DI - Hilt  
test - Junit  
## 기술정보  
- 100% Compose로 작업했습니다. 기존의 프로젝트들의 경우 View로 작업한걸 Compose로 변환했지만
  이번 프로젝트의 경우에는 MapleMFinder도 MapleFinder도 Compose로 작업할것입니다.
  그에따라서 Ui 최적화를 위한 State관리에 대한 공부를 했고 공부한 내용을 적용했습니다.
- build-logic 모듈을 통해 멀티모듈에서도 의존성 관리를 보다 쉽게 했습니다.
  (Hilt, Feature, Android) 의존성을 관리
## 느낀점  
MapleMFinder의 경우 처음으로 100% compose로 작업했고 bottomNavigationBar가 없는 첫번째 프로젝트입니다.  
ui 구상을 처음부터 compose로 작업했기때문에 만족스러운 ui가 나왔습니다.  

캐릭터 정보 상세보기 페이지를 만들때 캐릭터 정보, 스텟, 장비들이 각각 다른 response이므로  
세개의 정보들을 하나의 함수로 받을때 coroutine을 통해 콜백헬 없이 간편하게 정보들을 받았습니다.  

MapleM 게임의 경우 제공해주는 api가 적기때문에 MapleMFinder는 캐릭터 기본정보 조회정도에 그쳤지만  
MapleStory의 경우 nexon open api에서 더 많은 정보들을 제공하기 때문에 더욱 더 양질의 앱을 만들겠습니다.  
## 진척도  
[진척도](https://github.com/hegunhee/MapleMFinder/issues/1)  

## issue  
[png 파일의 color가 흑백으로 나온다면](https://github.com/hegunhee/MapleMFinder/issues/9)

import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:table_calendar/table_calendar.dart';
import 'package:todotodo/my_style.dart';

class RoutineDetailPage extends StatefulWidget {
  RoutineDetailPage({super.key});
  //int routineID;
  @override
  State<RoutineDetailPage> createState() => _RoutineDetailPageState();
}

class _RoutineDetailPageState extends State<RoutineDetailPage> {

  /// data base에서 로딩하기 by routineID
  /// FutureBuilder 적용하기
  Map<DateTime, bool> routineDone = {
    DateTime.utc(2024, 2, 1): true,
    DateTime.utc(2024, 2, 4): true,
    DateTime.utc(2024, 2, 14): true,
    DateTime.utc(2024, 2, 15): true,
    DateTime.utc(2024, 2, 17): true,
    DateTime.utc(2024, 2, 18): true,
    DateTime.utc(2024, 2, 20): true,
    DateTime.utc(2024, 2, 23): true,
  };

  //int id;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: CustomScrollView(
          slivers: [
            SliverAppBar(
              leading: IconButton(
                icon: Icon(Icons.arrow_back, color: Colors.black),
                onPressed: () {
                  // 뒤로 가기 기능
                },
              ),
              actions: <Widget>[
                IconButton(
                  icon: Icon(Icons.more_vert, color: Colors.black),
                  onPressed: () {
                    // 옵션 버튼 기능.
                  },
                ),
              ],
            ),
            SliverToBoxAdapter(
              child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('루틴', style: MyTextStyles.h1,),
                  Row(
                    children: [
                      Text('2',style: MyTextStyles.h1.copyWith(color: MyColors.green),),
                      Text('일 연속 성공', style: MyTextStyles.h2,),
                    ],
                  ),
                  Text('시작일 2022/22/22', style: MyTextStyles.s1,),
                  TableCalendar(
                    headerStyle: HeaderStyle(
                      formatButtonVisible: false,
                      titleCentered: true
                    ),
                    calendarStyle: CalendarStyle(
                      // 오늘 날짜 스타일을 다른 날짜와 동일하게 설정
                      todayTextStyle: TextStyle(color: Colors.black),
                      todayDecoration: BoxDecoration(
                        shape: BoxShape.circle, // 원형 표시
                        color: Colors.transparent, // 배경색을 투명하게 설정하여 특별한 표시를 하지 않음
                      ),
                      // 선택된 날짜 스타일 설정 (필요한 경우)
                      selectedTextStyle: TextStyle(color: Colors.black),
                      selectedDecoration: BoxDecoration(
                        shape: BoxShape.circle,
                        color: Colors.transparent,
                      ),
                    ),
                    rowHeight: 64,
                    daysOfWeekHeight: 48,
                    firstDay: DateTime.utc(2010, 10, 16),
                    lastDay: DateTime.utc(2030, 3, 14),
                    focusedDay: DateTime.now(),
                    eventLoader: (day) {
                      if (routineDone[day] != null && routineDone[day] == true) {
                        return ['Done'];
                      }
                      return [];
                    },
                    calendarBuilders: CalendarBuilders(  
                      outsideBuilder: (context, day, focusedDay) {
                        return Column(
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            Expanded(
                              child: Center(
                                child: Text(
                                  '${day.day}',
                                  style: TextStyle(color: Colors.grey),
                                ),
                              ),
                            ),
                            // 체크박스가 없으므로 빈 Container를 추가하여 마커가 있는 날짜와 높이를 맞춤
                            Container(height: 20),
                          ],
                        );
                      },
                      todayBuilder: (context, day, focusedDay) {
                        // 오늘 날짜 셀 레이아웃 정의
                        return Column(
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            Expanded(
                              child: Center(
                                child: Text(
                                  '${day.day}',
                                  style: TextStyle(color: Colors.black),
                                ),
                              ),
                            ),
                            // 체크박스가 없으므로 빈 Container를 추가하여 마커가 있는 날짜와 높이를 맞춤
                            Container(height: 20),
                          ],
                        );
                      },
                      defaultBuilder: (context, day, focusedDay) {
                        // 체크박스가 없는 날짜에 대한 기본 레이아웃 정의
                        return Column(
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            Expanded(
                              child: Center(
                                child: Text(
                                  '${day.day}',
                                  style: TextStyle(color: Colors.black),
                                ),
                              ),
                            ),
                            // 체크박스가 없으므로 빈 Container를 추가하여 마커가 있는 날짜와 높이를 맞춤
                            Container(height: 20),
                          ],
                        );
                      },
                      markerBuilder: (context, date, events) {
                        if (events.isNotEmpty) {
                          // 이벤트가 있는 날짜에 대한 마커 레이아웃 정의
                          return Positioned(
                            bottom: 0,
                            child: Text('✅', style: TextStyle(fontSize: 20.0)),
                          );
                        }
                        // 이벤트가 없으면 아무 것도 반환하지 않음
                        return null;
                      },
                    ),
                      
                    ),
                ],
              ),
                      ),
            ),
          ],
        ),
      ),
    );
  }
}

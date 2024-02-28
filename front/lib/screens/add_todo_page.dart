import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:todotodo/my_style.dart';
import 'package:todotodo/widget/add_routine/add_routine_date_card.dart';
import 'package:todotodo/widget/add_routine/add_routine_dotype_card.dart';
import 'package:todotodo/widget/add_routine/add_routine_name_card.dart';
import 'package:todotodo/widget/sliver_padding_box.dart';
import 'package:todotodo/widget/sliver_spacer.dart';

// ignore: must_be_immutable
class AddTodoPage extends StatefulWidget {
  AddTodoPage({super.key});
  String todoName = "";
  DateTime todoDate = DateTime.now();
  String doType = "do";
  @override
  State<AddTodoPage> createState() => _AddTodoPageState();
}

class _AddTodoPageState extends State<AddTodoPage> {
  TextEditingController textEditingController = TextEditingController();
  void onTextChange(String value){
    setState(() {
      widget.todoName = textEditingController.text;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: MyColors.beige,
      body: CustomScrollView(
        slivers: [
          SliverAppBar(
            backgroundColor: MyColors.beige,
            leading: IconButton(
              icon: Icon(Icons.arrow_back, color: Colors.black),
              onPressed: () {
                // 뒤로 가기 기능
              },
            ),
          ),
          SliverPaddingBox(child: AddRoutineNameCard(
            textEditingController: textEditingController,
            onChanged: onTextChange,
            )
          ),
          SliverSpacer(),
          SliverPaddingBox(child: AddRoutineDateCard(headText: '🗓️ 투두 날짜')),
          //SliverPaddingBox(child: AddRoutineDateCard(headText: '🔥 종료일')),
          SliverSpacer(),
          //SliverPaddingBox(child: AddRoutineDoTypeCard())
        ],
      ),
    );
  }
}
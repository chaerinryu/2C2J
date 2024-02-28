import 'package:flutter/material.dart';

class SliverPaddingBox extends StatelessWidget {
  const SliverPaddingBox({super.key, required this.child});
  final Widget child;
  @override
  Widget build(BuildContext context) {
    return SliverToBoxAdapter(
      child: Padding(
        padding: EdgeInsets.fromLTRB(16, 0, 16, 0),
        child: child,
      ),
    );
  }
}
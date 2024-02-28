import 'package:flutter/material.dart';

class SliverSpacer extends StatelessWidget {
  const SliverSpacer({super.key});

  @override
  Widget build(BuildContext context) {
    return SliverToBoxAdapter(
            child: SizedBox(
              height: 10,
            ),
          );
  }
}
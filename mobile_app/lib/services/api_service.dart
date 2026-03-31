import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/etudiant.dart';

class ApiService {
  // Pour émulateur Android → 10.0.2.2
  // Pour émulateur iOS ou vrai téléphone → IP de ta machine (ex: 192.168.1.100)
  static const String baseUrl = 'http://10.0.2.2:8080/api/etudiants';

  Future<List<Etudiant>> fetchEtudiants() async {
    final response = await http.get(Uri.parse(baseUrl));

    if (response.statusCode == 200) {
      final List<dynamic> data = json.decode(response.body);
      return data.map((json) => Etudiant.fromJson(json)).toList();
    } else {
      throw Exception('Échec du chargement des étudiants');
    }
  }
}
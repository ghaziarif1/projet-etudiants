import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/etudiant.dart';
import '../models/departement.dart';

class ApiService {
  // Pour émulateur Android → 10.0.2.2
  // Pour émulateur iOS ou vrai téléphone → IP de ta machine (ex: 192.168.1.100)
  static const String baseUrl = 'http://10.0.2.2:8080';

  Future<List<Departement>> fetchDepartements() async {
    final response = await http.get(Uri.parse('$baseUrl/api/departements'));

    if (response.statusCode == 200) {
      final List<dynamic> data = json.decode(response.body);
      return data.map((json) => Departement.fromJson(json)).toList();
    } else {
      throw Exception('Échec du chargement des départements');
    }
  }

  Future<List<Etudiant>> fetchEtudiantsByDepartement(int departementId) async {
    final response = await http.get(Uri.parse('$baseUrl/api/etudiants?departementId=$departementId'));

    if (response.statusCode == 200) {
      final List<dynamic> data = json.decode(response.body);
      return data.map((json) => Etudiant.fromJson(json)).toList();
    } else {
      throw Exception('Échec du chargement des étudiants');
    }
  }
}
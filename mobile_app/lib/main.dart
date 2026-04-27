import 'package:flutter/material.dart';
import 'models/etudiant.dart';
import 'models/departement.dart';
import 'services/api_service.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Étudiants',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: const EtudiantsPage(),
    );
  }
}

class EtudiantsPage extends StatefulWidget {
  const EtudiantsPage({super.key});
  @override
  State<EtudiantsPage> createState() => _EtudiantsPageState();
}

class _EtudiantsPageState extends State<EtudiantsPage> {
  late Future<List<Departement>> futureDepartements;
  List<Etudiant> etudiants = [];
  Departement? selectedDepartement;

  @override
  void initState() {
    super.initState();
    futureDepartements = ApiService().fetchDepartements();
  }

  void _onDepartementChanged(Departement? departement) {
    setState(() {
      selectedDepartement = departement;
      if (departement != null) {
        ApiService().fetchEtudiantsByDepartement(departement.id).then((data) {
          setState(() {
            etudiants = data;
          });
        }).catchError((error) {
          setState(() {
            etudiants = [];
          });
          ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(content: Text('Erreur : $error')),
          );
        });
      } else {
        setState(() {
          etudiants = [];
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Liste des Étudiants')),
      body: Column(
        children: [
          FutureBuilder<List<Departement>>(
            future: futureDepartements,
            builder: (context, snapshot) {
              if (snapshot.connectionState == ConnectionState.waiting) {
                return const Center(child: CircularProgressIndicator());
              }
              if (snapshot.hasError) {
                return Center(child: Text('Erreur : ${snapshot.error}'));
              }

              final departements = snapshot.data!;
              return Padding(
                padding: const EdgeInsets.all(16.0),
                child: DropdownButtonFormField<Departement>(
                  value: selectedDepartement,
                  hint: const Text('Sélectionnez un département'),
                  items: departements.map((departement) {
                    return DropdownMenuItem<Departement>(
                      value: departement,
                      child: Text(departement.nom),
                    );
                  }).toList(),
                  onChanged: _onDepartementChanged,
                ),
              );
            },
          ),
          Expanded(
            child: ListView.builder(
              itemCount: etudiants.length,
              itemBuilder: (context, index) {
                final e = etudiants[index];
                return Card(
                  margin: const EdgeInsets.all(8),
                  child: ListTile(
                    leading: CircleAvatar(child: Text(e.cin.substring(0, 2))),
                    title: Text(e.nom),
                    subtitle: Text('CIN : ${e.cin}'),
                    trailing: Text(e.dateNaissance),
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
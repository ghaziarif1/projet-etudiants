import Link from 'next/link'

async function getEtudiants() {
  const res = await fetch('http://localhost:8080/api/etudiants')
  if (!res.ok) {
    throw new Error('Failed to fetch etudiants')
  }
  return res.json()
}

export default async function EtudiantsPage() {
  const etudiants = await getEtudiants()

  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold">Étudiants</h1>
        <Link href="/etudiants/new" className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
          Ajouter Étudiant
        </Link>
      </div>
      <div className="grid gap-4">
        {etudiants.map((etudiant: any) => (
          <div key={etudiant.id} className="bg-white p-4 rounded shadow">
            <h2 className="text-xl font-semibold">{etudiant.nom}</h2>
            <p>CIN: {etudiant.cin}</p>
            <p>Date de naissance: {etudiant.dateNaissance}</p>
            <Link href={`/etudiants/${etudiant.id}`} className="text-blue-500 hover:underline">
              Voir détails
            </Link>
          </div>
        ))}
      </div>
    </div>
  )
}
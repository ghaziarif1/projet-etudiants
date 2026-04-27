async function getDepartements() {
  const res = await fetch('http://localhost:8080/api/departements')
  if (!res.ok) {
    throw new Error('Failed to fetch departements')
  }
  return res.json()
}

export default async function DepartementsPage() {
  const departements = await getDepartements()

  return (
    <div>
      <h1 className="text-3xl font-bold mb-6">Départements</h1>
      <div className="grid gap-4">
        {departements.map((departement: any) => (
          <div key={departement.id} className="bg-white p-4 rounded shadow">
            <h2 className="text-xl font-semibold">{departement.nom}</h2>
          </div>
        ))}
      </div>
    </div>
  )
}